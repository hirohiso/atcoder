import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MainTest extends Specification {
    //@formatter:off
    @Shared
    def input = [
"""\
5 2
2 2 1
2 1 3

""",
"""\
5 3
2 2 1
2 1 3
4 4 1

""",
"""\
5000 1
1 1 4999

""",
"""\
5000 1
1 1 1

""",
"""\
5 3
2 2 1
2 1 3
4 1 1
""",
    ]
    @Shared
    def output = [
"""\
12
""",
"""\
14
""",
"""\
12502500
""",
"""\
3
""",
"""\
12
""",
    ]
    //@formatter:on


    def "test"() {
        given:
        def is = new ByteArrayInputStream(inputStr.getBytes("UTF-8"))
        def bos = new ByteArrayOutputStream()
        def ps = new PrintStream(bos)

        when:
        Main.solve(is, ps)

        then:
        def act = bos.toString()
        exp == act

        where:
        inputStr << input
        exp << output
    }


}
