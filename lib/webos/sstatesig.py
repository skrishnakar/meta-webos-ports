import bb.siggen
import oe.sstatesig

# Imported from oe-core 421e927bd453259f4b3cdbd1676f6e12f97bf34f

# Our change:
# GF-51950 Build doesn't recognize bbappend-ed cmake-modules-webos-native as having changed
# Allow to cross target-native/cross/nativesdk boundaries

def sstate_rundepfilter(siggen, fn, recipename, task, dep, depname, dataCache):
    # Return True if we should keep the dependency, False to drop it
    def isNative(x):
        return x.endswith("-native")
    def isCross(x):
        return x.endswith("-cross") or x.endswith("-cross-initial") or x.endswith("-cross-intermediate")
    def isNativeSDK(x):
        return x.startswith("nativesdk-")
    def isKernel(fn):
        inherits = " ".join(dataCache.inherits[fn])
        return inherits.find("module-base.bbclass") != -1 or inherits.find("linux-kernel-base.bbclass") != -1
    def isPackageGroup(fn):
        inherits = " ".join(dataCache.inherits[fn])
        return "packagegroup.bbclass" in inherits
    def isImage(fn):
        return "image.bbclass" in " ".join(dataCache.inherits[fn])

    # Always include our own inter-task dependencies
    if recipename == depname:
        return True

    # Quilt (patch application) changing isn't likely to affect anything
    excludelist = ['quilt-native', 'subversion-native', 'git-native']
    if depname in excludelist and recipename != depname:
        return False

    # Don't change native/cross/nativesdk recipe dependencies any further
    if isNative(recipename) or isCross(recipename) or isNativeSDK(recipename):
        return True

    # Only target packages beyond here

    # packagegroups are assumed to have well behaved names which don't change between architecures/tunes
    if isPackageGroup(fn):
        return False  

    # GF-51950 Build doesn't recognize bbappend-ed cmake-modules-webos-native as having changed
    # Allow to cross target-native/cross/nativesdk boundaries
    # Drop native/cross/nativesdk dependencies from target recipes
    # if isNative(depname) or isCross(depname) or isNativeSDK(depname):
    #    return False

    # Exclude well defined machine specific configurations which don't change ABI
    if depname in siggen.abisaferecipes and not isImage(fn):
        return False

    # Exclude well defined recipe->dependency
    if "%s->%s" % (recipename, depname) in siggen.saferecipedeps:
        return False

    # Kernel modules are well namespaced. We don't want to depend on the kernel's checksum
    # if we're just doing an RRECOMMENDS_xxx = "kernel-module-*", not least because the checksum
    # is machine specific.
    # Therefore if we're not a kernel or a module recipe (inheriting the kernel classes)
    # and we reccomend a kernel-module, we exclude the dependency.
    depfn = dep.rsplit(".", 1)[0]
    if dataCache and isKernel(depfn) and not isKernel(fn):
        for pkg in dataCache.runrecs[fn]:
            if " ".join(dataCache.runrecs[fn][pkg]).find("kernel-module-") != -1:
                return False

    # Default to keep dependencies
    return True

class SignatureGeneratorOEBasicHashStrict(oe.sstatesig.SignatureGeneratorOEBasicHash):
    name = "OEBasicHashStrict"
    def rundep_check(self, fn, recipename, task, dep, depname, dataCache = None):
        return sstate_rundepfilter(self, fn, recipename, task, dep, depname, dataCache)

# Insert these classes into siggen's namespace so it can see and select them
bb.siggen.SignatureGeneratorOEBasicHashStrict = SignatureGeneratorOEBasicHashStrict
