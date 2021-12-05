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
1 1 2
2 2 3
3 2 4

""",
"""\
19
4 210068409 221208102
4 16698200 910945203
4 76268400 259148323
4 370943597 566244098
1 428897569 509621647
4 250946752 823720939
1 642505376 868415584
2 619091266 868230936
2 306543999 654038915
4 486033777 715789416
1 527225177 583184546
2 885292456 900938599
3 264004185 486613484
2 345310564 818091848
1 152544274 521564293
4 13819154 555218434
3 507364086 545932412
4 797872271 935850549
2 415488246 685203817

""",
    ]
    @Shared
    def output = [
"""\
2
""",
"""\
102
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
