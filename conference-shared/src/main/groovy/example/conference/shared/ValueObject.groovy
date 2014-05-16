package example.conference.shared

import groovy.transform.AnnotationCollector
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

//@Builder http://jira.codehaus.org/browse/GROOVY-6774
@TupleConstructor
@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
@AnnotationCollector()
@interface ValueObject {
}
