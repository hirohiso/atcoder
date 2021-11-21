import spock.lang.Shared
import spock.lang.Specification

class MainTest extends Specification {
    @Shared
    def input = [
            """\
16
""",
            """\
2
""",
            """\
183
""",
    ]
    @Shared
    def output = [
            """\
pon
""",
            """\
hon
""",
            """\
bon
""",

    ]


    def "test"() {
        given:
        def is = new ByteArrayInputStream(inputStr.getBytes("UTF-8"))
        def bos = new ByteArrayOutputStream()
        def ps = new PrintStream(bos)

        when:
        Main.solve(is,ps)

        then:
        def act =  bos.toString()
        exp == act

        where:
        inputStr << input
        exp << output
    }


}
