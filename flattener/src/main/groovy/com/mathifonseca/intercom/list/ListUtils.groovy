package com.mathifonseca.intercom.list

class ListUtils {

    static Collection flatten(Collection list) {
        if (list == null) {
            throw new IllegalArgumentException()
        }
        return flattenRecursive(list, [])
    }

    private static Collection flattenRecursive(Collection list, Collection flattened) {
        list.each { item ->
            if (item instanceof List) {
                flattenRecursive(item, flattened)
            } else {
                flattened << item
            }
        }
        return flattened
    }

}
