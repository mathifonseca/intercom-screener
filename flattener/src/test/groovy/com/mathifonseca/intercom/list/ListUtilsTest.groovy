package com.mathifonseca.intercom.list

import spock.lang.Specification

class ListUtilsTest extends Specification {

    def "test flatten null list"() {
        given: "a null list"
            List list = null
        when: "flatten method is called"
            ListUtils.flatten(list)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test flatten empty list"() {
        given: "an empty list"
            List list = []
        when: "flatten method is called"
            List flattened = ListUtils.flatten(list)
        then: "an empty list is returned"
            flattened == []
    }

    def "test flatten one level"() {
        given: "A simple integer list"
            List list = [ 1, 2, 3, 4, 5 ]
        when: "flatten method is called"
            List flattened = ListUtils.flatten(list)
        then: "the same list is returned"
            flattened == list
    }

    def "test flatten one level terribly long list"() {
        given: "A very long integer list"
            List list = []
            (1..1000000).each {
                list << it
            }
        when: "flatten method is called"
            List flattened = ListUtils.flatten(list)
        then: "the same list is returned"
            flattened == list
    }

    def "test one item and multiple levels"() {
        when: "flatten method is called"
            List flattened = ListUtils.flatten(list)
        then: "flattened list is returned"
            flattened == expected
        where:
            list        | expected
            [1]         | [1]
            [[1]]       | [1]
            [[[[[1]]]]] | [1]
    }

    def "test multiple items and multiple levels"() {
        when: "flatten method is called"
            List flattened = ListUtils.flatten(list)
        then: "flattened list is returned"
            flattened == expected
        where:
            list | expected
            [1, [2, [3, [4, [5, [6]]]]]] | [1, 2, 3, 4, 5, 6]
            [[1], [[2]], [[[3]]]] | [1, 2, 3]
            [1, [2, 3], [4, [5, 6], 7, [8, [[9]]]]] | [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

}
