import old.jsc2019_qual.b.Main
import spock.lang.Shared
import spock.lang.Specification

class MainTest extends Specification {
    @Shared
    def input = [
            """\
2 2
2 1
""",
            """\
3 5
1 1 1
""",
            """\
10 998244353
10 9 8 7 5 6 3 4 2 1
""",
            """\
50 50
4 7 2 10 1 3 9 9 3 10 1 1 2 6 4 1 3 3 9 7 1 8 4 9 10 9 7 4 10 7 8 2 1 6 10 9 2 6 5 7 2 1 5 8 2 1 4 2 7 2
""",
    ]
    @Shared
    def output = [
            """\
3
""",
            """\
0
""",
            """\
185297239
""",
            """\
1384050
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
