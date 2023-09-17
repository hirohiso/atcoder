import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MainTest extends Specification {
    //@formatter:off
    @Shared
    def input = [
"""\
299999

""",
    ]
    @Shared
    def output = [
"""\
186783
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

    def "testFiles"() {
        given:
        def input = new File("./src/test/resources/case1/in.txt").getText()
        def output = new File("./src/test/resources/case1/out.txt").getText()

        def is = new ByteArrayInputStream(input.getBytes("UTF-8"))
        def bos = new ByteArrayOutputStream()
        def ps = new PrintStream(bos)

        when:
        Main.solve(is, ps)

        then:
        def act = bos.toString()
        output == act

    }

}
