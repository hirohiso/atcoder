import spock.lang.Shared
import spock.lang.Specification

class MainTest extends Specification {
    @Shared
    def input = [
            """\
4
2 1 2
2 1 1
2 2 1
2 1 2
""",
            """\
5
1 1
1 1
1 2
2 1 1
3 1 1 1
""",
            """\
1
1 1
""",
    ]
    @Shared
    def output = [
            """\
3
""",
            """\
4
""",
            """\
1
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
