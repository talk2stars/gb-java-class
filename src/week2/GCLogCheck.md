Analysis:

serial：
年轻代叫def new，年老代叫tenured，
512m时发生了10多次GC，有3-4次是年老代，每次大概30ms多，还有一次Full GC 
2g时发生了5次GC，都是年轻代，每次大概85ms左右

Parallel：
年轻代叫PSYoungGen，年老代叫ParOldGen，
512m时发生了30多次GC，有3-4次是年老代，从10ms到30ms多，还有多次Full GC 
2g时发生了6次GC，都是年轻代，从35ms 到74ms，第一次最长。

CMS：
年轻代叫par new generation，年老代叫concurrent mark-sweep generation，
512m时发生了10多次GC，都是年轻代，从6ms到30ms多，没有Full GC 
2g时发生了5次GC，都是年轻代，从50ms 到80ms多。

G1：
512m时，相比其他GC，发生了最多GC，但大都是几ms，Full GC也小于30ms 
2g时发生了7次GC，都是年轻代，都小于10ms。

总体来说，512m到2g增加内存后，GC发生频率降低（但是每次GC的耗时有增加），总耗时会降低。



Serial GC Logs:

-Xmx2g -Xms2g

2021-09-26T15:53:20.189+0800: 0.359: [GC (Allocation Failure) 2021-09-26T15:53:20.189+0800: 0.359: [DefNew: 559232K->69887K(629120K), 0.0876289 secs] 559232K->151661K(2027264K), 0.0877502 secs] [Times: user=0.05 sys=0.04, real=0.09 secs]
2021-09-26T15:53:20.367+0800: 0.537: [GC (Allocation Failure) 2021-09-26T15:53:20.367+0800: 0.537: [DefNew: 629119K->69887K(629120K), 0.1173825 secs] 710893K->284940K(2027264K), 0.1174947 secs] [Times: user=0.06 sys=0.05, real=0.12 secs]
2021-09-26T15:53:20.551+0800: 0.721: [GC (Allocation Failure) 2021-09-26T15:53:20.551+0800: 0.721: [DefNew: 629119K->69887K(629120K), 0.0833392 secs] 844172K->405749K(2027264K), 0.0834473 secs] [Times: user=0.05 sys=0.04, real=0.08 secs]
2021-09-26T15:53:20.699+0800: 0.869: [GC (Allocation Failure) 2021-09-26T15:53:20.700+0800: 0.869: [DefNew: 629119K->69887K(629120K), 0.0813656 secs] 964981K->525101K(2027264K), 0.0814568 secs] [Times: user=0.05 sys=0.03, real=0.09 secs]
2021-09-26T15:53:20.845+0800: 1.015: [GC (Allocation Failure) 2021-09-26T15:53:20.846+0800: 1.015: [DefNew: 629119K->69888K(629120K), 0.0865485 secs] 1084333K->643031K(2027264K), 0.0866484 secs] [Times: user=0.05 sys=0.03, real=0.09 secs]

-Xmx512m -Xms512m

2021-09-26T15:52:49.633+0800: 0.212: [GC (Allocation Failure) 2021-09-26T15:52:49.633+0800: 0.212: [DefNew: 139776K->17472K(157248K), 0.0282399 secs] 139776K->46628K(506816K), 0.0283656 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
2021-09-26T15:52:49.685+0800: 0.264: [GC (Allocation Failure) 2021-09-26T15:52:49.685+0800: 0.264: [DefNew: 157248K->17469K(157248K), 0.0328347 secs] 186404K->86446K(506816K), 0.0329220 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
...
2021-09-26T15:52:50.054+0800: 0.633: [GC (Allocation Failure) 2021-09-26T15:52:50.054+0800: 0.633: [DefNew: 139776K->17471K(157248K), 0.0054917 secs] 409766K->312621K(506816K), 0.0055763 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
...
2021-09-26T15:52:50.382+0800: 0.961: [GC (Allocation Failure) 2021-09-26T15:52:50.382+0800: 0.961: [DefNew: 139731K->139731K(157248K), 0.0000188 secs]
2021-09-26T15:52:50.382+0800: 0.961: [Tenured: 345018K->349497K(349568K), 0.0422621 secs] 484750K->350703K(506816K), [Metaspace: 2576K->2576K(1056768K)], 0.0423679 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2021-09-26T15:52:50.444+0800: 1.023: [Full GC (Allocation Failure) 2021-09-26T15:52:50.444+0800: 1.023: [Tenured: 349497K->341770K(349568K), 0.0452964 secs] 506548K->341770K(506816K), [Metaspace: 2576K->2576K(1056768K)], 0.0453981 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2021-09-26T15:52:50.506+0800: 1.085: [GC (Allocation Failure) 2021-09-26T15:52:50.506+0800: 1.085: [DefNew: 139776K->139776K(157248K), 0.0000170 secs]2021-09-26T15:52:50.506+0800: 1.085: [Tenured: 341770K->349566K(349568K), 0.0290273 secs] 481546K->361740K(506816K), [Metaspace: 2576K->2576K(1056768K)], 0.0291485 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]

Parallel GC Logs:

-Xmx2g -Xms2g

2021-09-26T15:40:55.403+0800: 0.457: [GC (Allocation Failure) [PSYoungGen: 524800K->87033K(611840K)] 524800K->138173K(2010112K), 0.0738478 secs] [Times: user=0.07 sys=0.09, real=0.07 secs]
2021-09-26T15:40:55.575+0800: 0.629: [GC (Allocation Failure) [PSYoungGen: 611833K->87032K(611840K)] 662973K->259397K(2010112K), 0.0607984 secs] [Times: user=0.09 sys=0.19, real=0.06 secs]
2021-09-26T15:40:55.701+0800: 0.755: [GC (Allocation Failure) [PSYoungGen: 611832K->87039K(611840K)] 784197K->377591K(2010112K), 0.0397653 secs] [Times: user=0.11 sys=0.09, real=0.04 secs]
2021-09-26T15:40:55.806+0800: 0.860: [GC (Allocation Failure) [PSYoungGen: 611839K->87034K(611840K)] 902391K->484360K(2010112K), 0.0395143 secs] [Times: user=0.11 sys=0.11, real=0.04 secs]
2021-09-26T15:40:55.913+0800: 0.967: [GC (Allocation Failure) [PSYoungGen: 611834K->87033K(611840K)] 1009160K->589510K(2010112K), 0.0368811 secs] [Times: user=0.11 sys=0.11, real=0.04 secs]
2021-09-26T15:40:56.011+0800: 1.065: [GC (Allocation Failure) [PSYoungGen: 611833K->87035K(322048K)] 1114310K->706815K(1720320K), 0.0394236 secs] [Times: user=0.11 sys=0.12, real=0.04 secs]

-Xmx512m -Xms512m

2021-09-26T15:42:10.212+0800: 0.132: [GC (Allocation Failure) [PSYoungGen: 131371K->21490K(153088K)] 131371K->47445K(502784K), 0.0145523 secs] [Times: user=0.03 sys=0.07, real=0.01 secs]
2021-09-26T15:42:10.246+0800: 0.166: [GC (Allocation Failure) [PSYoungGen: 153074K->21493K(153088K)] 179029K->88205K(502784K), 0.0169066 secs] [Times: user=0.02 sys=0.08, real=0.02 secs]
2021-09-26T15:42:10.280+0800: 0.200: [GC (Allocation Failure) [PSYoungGen: 153077K->21500K(153088K)] 219789K->125660K(502784K), 0.0117655 secs] [Times: user=0.03 sys=0.05, real=0.01 secs]
2021-09-26T15:42:10.308+0800: 0.228: [GC (Allocation Failure) [PSYoungGen: 152947K->21502K(153088K)] 257107K->171891K(502784K), 0.0136200 secs] [Times: user=0.04 sys=0.06, real=0.02 secs]
...
2021-09-26T15:42:10.446+0800: 0.367: [GC (Allocation Failure) [PSYoungGen: 103255K->22531K(116736K)] 387147K->347330K(466432K), 0.0128721 secs] [Times: user=0.02 sys=0.06, real=0.01 secs]
2021-09-26T15:42:10.459+0800: 0.379: [Full GC (Ergonomics) [PSYoungGen: 22531K->0K(116736K)] [ParOldGen: 324798K->248968K(349696K)] 347330K->248968K(466432K), [Metaspace: 2576K->2576K(1056768K)], 0.0304872 secs] [Times: user=0.19 sys=0.01, real=0.04 secs]
2021-09-26T15:42:10.499+0800: 0.419: [GC (Allocation Failure) [PSYoungGen: 58565K->19622K(116736K)] 307534K->268590K(466432K), 0.0016992 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
...
2021-09-26T15:42:10.532+0800: 0.452: [GC (Allocation Failure) [PSYoungGen: 81704K->18416K(116736K)] 365535K->323995K(466432K), 0.0029534 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
2021-09-26T15:42:10.535+0800: 0.455: [Full GC (Ergonomics) [PSYoungGen: 18416K->0K(116736K)] [ParOldGen: 305579K->266061K(349696K)] 323995K->266061K(466432K), [Metaspace: 2576K->2576K(1056768K)], 0.0288570 secs] [Times: user=0.18 sys=0.00, real=0.03 secs]
2021-09-26T15:42:10.577+0800: 0.497: [GC (Allocation Failure) [PSYoungGen: 58880K->16248K(116736K)] 324941K->282309K(466432K), 0.0029484 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
...



CMS GC Logs:

-Xmx2g -Xms2g

2021-09-26T15:33:23.126+0800: 0.422: [GC (Allocation Failure) 2021-09-26T15:33:23.126+0800: 0.422: [ParNew: 559232K->69888K(629120K), 0.0650834 secs] 559232K->164166K(2027264K), 0.0652216 secs] [Times: user=0.12 sys=0.13, real=0.07 secs]
2021-09-26T15:33:23.288+0800: 0.584: [GC (Allocation Failure) 2021-09-26T15:33:23.289+0800: 0.584: [ParNew: 629120K->69888K(629120K), 0.0527571 secs] 723398K->297278K(2027264K), 0.0528573 secs] [Times: user=0.10 sys=0.16, real=0.06 secs]
2021-09-26T15:33:23.408+0800: 0.704: [GC (Allocation Failure) 2021-09-26T15:33:23.408+0800: 0.704: [ParNew: 629120K->69888K(629120K), 0.0740299 secs] 856510K->417169K(2027264K), 0.0741240 secs] [Times: user=0.44 sys=0.04, real=0.08 secs]
2021-09-26T15:33:23.545+0800: 0.841: [GC (Allocation Failure) 2021-09-26T15:33:23.546+0800: 0.841: [ParNew: 629120K->69888K(629120K), 0.0720751 secs] 976401K->532933K(2027264K), 0.0721737 secs] [Times: user=0.50 sys=0.03, real=0.07 secs]
2021-09-26T15:33:23.681+0800: 0.977: [GC (Allocation Failure) 2021-09-26T15:33:23.681+0800: 0.977: [ParNew: 629120K->69886K(629120K), 0.0825438 secs] 1092165K->658614K(2027264K), 0.0826526 secs] [Times: user=0.44 sys=0.05, real=0.08 secs]

-Xmx512m -Xms512m

2021-09-26T15:24:59.780+0800: 0.191: [GC (Allocation Failure) 2021-09-26T15:24:59.780+0800: 0.191: [ParNew: 139776K->17471K(157248K), 0.0135448 secs] 139776K->48521K(506816K), 0.0136574 secs] [Times: user=0.03 sys=0.04, real=0.01 secs]
2021-09-26T15:24:59.817+0800: 0.228: [GC (Allocation Failure) 2021-09-26T15:24:59.817+0800: 0.228: [ParNew: 157247K->17472K(157248K), 0.0155437 secs] 188297K->91811K(506816K), 0.0156064 secs] [Times: user=0.03 sys=0.03, real=0.02 secs]
2021-09-26T15:24:59.852+0800: 0.263: [GC (Allocation Failure) 2021-09-26T15:24:59.852+0800: 0.263: [ParNew: 157248K->17470K(157248K), 0.0354733 secs] 231587K->129908K(506816K), 0.0355500 secs] [Times: user=0.17 sys=0.02, real=0.03 secs]
2021-09-26T15:24:59.910+0800: 0.322: [GC (Allocation Failure) 2021-09-26T15:24:59.911+0800: 0.322: [ParNew: 156828K->17469K(157248K), 0.0277049 secs] 269266K->175141K(506816K), 0.0278070 secs] [Times: user=0.16 sys=0.01, real=0.02 secs]
2021-09-26T15:24:59.958+0800: 0.369: [GC (Allocation Failure) 2021-09-26T15:24:59.958+0800: 0.369: [ParNew: 156810K->17471K(157248K), 0.0281662 secs] 314483K->223900K(506816K), 0.0282629 secs] [Times: user=0.18 sys=0.02, real=0.03 secs]
2021-09-26T15:25:00.005+0800: 0.416: [GC (Allocation Failure) 2021-09-26T15:25:00.005+0800: 0.416: [ParNew: 157247K->17471K(157248K), 0.0387058 secs] 363676K->273775K(506816K), 0.0387929 secs] [Times: user=0.16 sys=0.02, real=0.04 secs]
2021-09-26T15:25:00.064+0800: 0.476: [GC (Allocation Failure) 2021-09-26T15:25:00.065+0800: 0.476: [ParNew: 156801K->17471K(157248K), 0.0391771 secs] 413105K->324687K(506816K), 0.0392746 secs] [Times: user=0.17 sys=0.02, real=0.04 secs]
2021-09-26T15:25:00.123+0800: 0.534: [GC (Allocation Failure) 2021-09-26T15:25:00.123+0800: 0.534: [ParNew: 157247K->157247K(157248K), 0.0000271 secs]2021-09-26T15:25:00.123+0800: 0.534: [Tenured: 307215K->262222K(349568K), 0.0534533 secs] 464463K->262222K(506816K), [Metaspace: 2576K->2576K(1056768K)], 0.0536121 secs] [Times: user=0.06 sys=0.00, real=0.05 secs]
2021-09-26T15:25:00.196+0800: 0.607: [GC (Allocation Failure) 2021-09-26T15:25:00.196+0800: 0.607: [ParNew: 139776K->17469K(157248K), 0.0065976 secs] 401998K->310647K(506816K), 0.0066833 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
2021-09-26T15:25:00.222+0800: 0.633: [GC (Allocation Failure) 2021-09-26T15:25:00.222+0800: 0.633: [ParNew: 157245K->17471K(157248K), 0.0198838 secs] 450423K->351057K(506816K), 0.0199970 secs] [Times: user=0.10 sys=0.01, real=0.02 secs]
2021-09-26T15:25:00.259+0800: 0.670: [GC (Allocation Failure) 2021-09-26T15:25:00.259+0800: 0.670: [ParNew: 157247K->157247K(157248K), 0.0000229 secs]2021-09-26T15:25:00.259+0800: 0.670: [Tenured: 333585K->304017K(349568K), 0.0439978 secs] 490833K->304017K(506816K), [Metaspace: 2576K->2576K(1056768K)], 0.0441374 secs] [Times: user=0.04 sys=0.00, real=0.05 secs]
2021-09-26T15:25:00.325+0800: 0.736: [GC (Allocation Failure) 2021-09-26T15:25:00.325+0800: 0.736: [ParNew: 139776K->139776K(157248K), 0.0000224 secs]2021-09-26T15:25:00.325+0800: 0.736: [Tenured: 304017K->317720K(349568K), 0.0431577 secs] 443793K->317720K(506816K), [Metaspace: 2576K->2576K(1056768K)], 0.0432895 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2021-09-26T15:25:00.390+0800: 0.801: [GC (Allocation Failure) 2021-09-26T15:25:00.390+0800: 0.801: [ParNew: 139776K->139776K(157248K), 0.0000239 secs]2021-09-26T15:25:00.390+0800: 0.801: [Tenured: 317720K->311197K(349568K), 0.0462783 secs] 457496K->311197K(506816K), [Metaspace: 2576K->2576K(1056768K)], 0.0464268 secs] [Times: user=0.05 sys=0.00, real=0.04 secs]



G1 GC Logs:

-Xmx2g -Xms2g

2021-09-25T23:00:00.032+0800: 0.154: [GC pause (G1 Evacuation Pause) (young), 0.0086205 secs]
2021-09-25T23:00:00.070+0800: 0.192: [GC pause (G1 Evacuation Pause) (young), 0.0073309 secs]
2021-09-25T23:00:00.100+0800: 0.222: [GC pause (G1 Evacuation Pause) (young), 0.0067600 secs]
2021-09-25T23:00:00.129+0800: 0.251: [GC pause (G1 Evacuation Pause) (young), 0.0071913 secs]
2021-09-25T23:00:00.153+0800: 0.275: [GC pause (G1 Evacuation Pause) (young), 0.0081670 secs]
2021-09-25T23:00:00.202+0800: 0.325: [GC pause (G1 Evacuation Pause) (young), 0.0129798 secs]
2021-09-25T23:00:00.244+0800: 0.366: [GC pause (G1 Evacuation Pause) (young), 0.0116343 secs]

-Xmx512m -Xms512m

2021-09-26T15:13:38.886+0800: 0.141: [GC pause (G1 Evacuation Pause) (young), 0.0038518 secs]
2021-09-26T15:13:38.899+0800: 0.153: [GC pause (G1 Evacuation Pause) (young), 0.0021603 secs]
2021-09-26T15:13:38.923+0800: 0.177: [GC pause (G1 Evacuation Pause) (young), 0.0048883 secs]
2021-09-26T15:13:38.942+0800: 0.197: [GC pause (G1 Evacuation Pause) (young), 0.0050516 secs]
2021-09-26T15:13:39.024+0800: 0.279: [GC pause (G1 Evacuation Pause) (young), 0.0127448 secs]
2021-09-26T15:13:39.055+0800: 0.309: [GC pause (G1 Evacuation Pause) (young), 0.0093292 secs]
2021-09-26T15:13:39.086+0800: 0.340: [GC pause (G1 Humongous Allocation) (young) (initial-mark), 0.0054311 secs]
2021-09-26T15:13:39.137+0800: 0.391: [GC pause (G1 Evacuation Pause) (young) (to-space exhausted), 0.0087828 secs]
2021-09-26T15:13:39.147+0800: 0.401: [GC pause (G1 Evacuation Pause) (mixed), 0.0025237 secs]
...
2021-09-26T15:13:39.486+0800: 0.740: [GC pause (G1 Humongous Allocation) (young) (initial-mark), 0.0008943 secs]
2021-09-26T15:13:39.493+0800: 0.748: [GC pause (G1 Evacuation Pause) (young), 0.0017694 secs]
2021-09-26T15:13:39.499+0800: 0.753: [GC pause (G1 Evacuation Pause) (mixed) (to-space exhausted), 0.0034711 secs]
2021-09-26T15:13:39.503+0800: 0.757: [GC pause (G1 Humongous Allocation) (young) (initial-mark), 0.0015078 secs]
2021-09-26T15:13:39.510+0800: 0.764: [GC pause (G1 Evacuation Pause) (young) (to-space exhausted), 0.0012152 secs]
2021-09-26T15:13:39.513+0800: 0.767: [GC pause (G1 Humongous Allocation) (mixed) (to-space exhausted), 0.0011193 secs]
2021-09-26T15:13:39.515+0800: 0.770: [GC pause (G1 Humongous Allocation) (mixed) (to-space exhausted), 0.0009716 secs]
2021-09-26T15:13:39.517+0800: 0.771: [GC pause (G1 Evacuation Pause) (young) (initial-mark) (to-space exhausted), 0.0006915 secs]
2021-09-26T15:13:39.518+0800: 0.772: [GC pause (G1 Evacuation Pause) (young) (to-space exhausted), 0.0005419 secs]
2021-09-26T15:13:39.518+0800: 0.773: [Full GC (Allocation Failure)  451M->334M(512M), 0.0386151 secs]
...





