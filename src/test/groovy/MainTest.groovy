import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MainTest extends Specification {
    //@formatter:off
    @Shared
    def input = [
"""\
3

""",
"""\
4

""",
"""\
2

""",
"""\
1

""",
"""\
6

""",
    ]
    @Shared
    def output = [
"""\
Yes
3
2 1 2
2 3 1
2 2 3
""",
"""\
No
""",
"""\
No
""",
"""\
Yes
2
2 1
2 1
""",
"""\
Yes
4
3 1 2 3 
3 1 4 5 
3 2 4 6
3 3 5 6
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
