package example.conference.registrations.api

import groovy.transform.AnnotationCollector
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@TupleConstructor
@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
@AnnotationCollector()
@interface ValueObject {
}
