package com.mathifonseca.intercom

import com.mathifonseca.intercom.list.ListUtils

class Main {

    static void main(args) {

        def list = [[1,2,[3]],4]

        def flattened = ListUtils.flatten(list)

        assert flattened == [1,2,3,4]

        println "$list -> $flattened"

    }

}
