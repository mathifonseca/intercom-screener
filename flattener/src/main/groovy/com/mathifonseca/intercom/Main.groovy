package com.mathifonseca.intercom

import com.mathifonseca.intercom.list.ListUtils

class Main {

    static void main(args) {

        List list = [[1, 2, [3]], 4]

        List flattened = ListUtils.flatten(list)

        assert flattened == [1, 2, 3, 4]

        println "$list -> $flattened"

    }

}
