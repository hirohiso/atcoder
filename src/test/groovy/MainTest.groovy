import spock.lang.Specification

class MainTest extends Specification {
    def "test"() {
        given:
        def input = "3.4\n"
        def is = new ByteArrayInputStream(input.getBytes("UTF-8"))
        def bos = new ByteArrayOutputStream()
        def ps = new PrintStream(bos)

        when:
        Main.solve(is,ps)

        then:
        println bos.toString()
    }
}
