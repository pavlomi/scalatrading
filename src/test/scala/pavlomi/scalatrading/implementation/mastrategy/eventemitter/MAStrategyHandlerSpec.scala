package pavlomi.scalatrading.implementation.mastrategy.eventemitter
import java.time.Instant

import pavlomi.scalatrading.domain.{Candlestick, Price, StockSymbol, Value}
import pavlomi.scalatrading.implementation.mastrategy.domain.MADataEvent

class MAStrategyHandlerSpec {}

object MAStrategyHandlerSpec {

  val maDataEvent180 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1646), Price(1.1619), Price(1.1659), Price(1.1607), Value(0), Instant.parse("2017-11-01T00:00:00Z")))
  val maDataEvent181 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1619), Price(1.1658), Price(1.1689), Price(1.1613), Value(0), Instant.parse("2017-11-02T00:00:00Z")))
  val maDataEvent182 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1660), Price(1.1609), Price(1.1692), Price(1.1599), Value(0), Instant.parse("2017-11-03T00:00:00Z")))
  val maDataEvent183 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1616), Price(1.1615), Price(1.1618), Price(1.1606), Value(0), Instant.parse("2017-11-05T00:00:00Z")))
  val maDataEvent184 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1609), Price(1.1610), Price(1.1625), Price(1.1579), Value(0), Instant.parse("2017-11-06T00:00:00Z")))
  val maDataEvent185 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1610), Price(1.1587), Price(1.1618), Price(1.1553), Value(0), Instant.parse("2017-11-07T00:00:00Z")))
  val maDataEvent186 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1587), Price(1.1595), Price(1.1612), Price(1.1578), Value(0), Instant.parse("2017-11-08T00:00:00Z")))
  val maDataEvent187 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1596), Price(1.1642), Price(1.1657), Price(1.1586), Value(0), Instant.parse("2017-11-09T00:00:00Z")))
  val maDataEvent188 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1642), Price(1.1665), Price(1.1680), Price(1.1623), Value(0), Instant.parse("2017-11-10T00:00:00Z")))
  val maDataEvent189 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1658), Price(1.1661), Price(1.1665), Price(1.1656), Value(0), Instant.parse("2017-11-12T00:00:00Z")))
  val maDataEvent190 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1666), Price(1.1667), Price(1.1677), Price(1.1638), Value(0), Instant.parse("2017-11-13T00:00:00Z")))
  val maDataEvent191 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1667), Price(1.1798), Price(1.1807), Price(1.1660), Value(0), Instant.parse("2017-11-14T00:00:00Z")))
  val maDataEvent192 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1797), Price(1.1792), Price(1.1861), Price(1.1784), Value(0), Instant.parse("2017-11-15T00:00:00Z")))
  val maDataEvent193 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1792), Price(1.1770), Price(1.1802), Price(1.1756), Value(0), Instant.parse("2017-11-16T00:00:00Z")))
  val maDataEvent194 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1771), Price(1.1795), Price(1.1823), Price(1.1766), Value(0), Instant.parse("2017-11-17T00:00:00Z")))
  val maDataEvent195 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1789), Price(1.1734), Price(1.1798), Price(1.1723), Value(0), Instant.parse("2017-11-19T00:00:00Z")))
  val maDataEvent196 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1789), Price(1.1732), Price(1.1808), Price(1.1721), Value(0), Instant.parse("2017-11-20T00:00:00Z")))
  val maDataEvent197 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1732), Price(1.1738), Price(1.1759), Price(1.1713), Value(0), Instant.parse("2017-11-21T00:00:00Z")))
  val maDataEvent198 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1738), Price(1.1822), Price(1.1829), Price(1.1733), Value(0), Instant.parse("2017-11-22T00:00:00Z")))
  val maDataEvent199 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1822), Price(1.1852), Price(1.1856), Price(1.1812), Value(0), Instant.parse("2017-11-23T00:00:00Z")))
  val maDataEvent200 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1850), Price(1.1930), Price(1.1944), Price(1.1835), Value(0), Instant.parse("2017-11-24T00:00:00Z")))
  val maDataEvent201 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1927), Price(1.1928), Price(1.1936), Price(1.1924), Value(0), Instant.parse("2017-11-26T00:00:00Z")))
  val maDataEvent202 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1933), Price(1.1898), Price(1.1962), Price(1.1896), Value(0), Instant.parse("2017-11-27T00:00:00Z")))
  val maDataEvent203 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1898), Price(1.1841), Price(1.1921), Price(1.1827), Value(0), Instant.parse("2017-11-28T00:00:00Z")))
  val maDataEvent204 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1841), Price(1.1848), Price(1.1883), Price(1.1816), Value(0), Instant.parse("2017-11-29T00:00:00Z")))
  val maDataEvent205 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1847), Price(1.1904), Price(1.1933), Price(1.1809), Value(0), Instant.parse("2017-11-30T00:00:00Z")))
  val maDataEvent206 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1904), Price(1.1891), Price(1.1942), Price(1.1851), Value(0), Instant.parse("2017-12-01T00:00:00Z")))
  val maDataEvent207 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1864), Price(1.1857), Price(1.1875), Price(1.1857), Value(0), Instant.parse("2017-12-03T00:00:00Z")))
  val maDataEvent208 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1882), Price(1.1866), Price(1.1890), Price(1.1830), Value(0), Instant.parse("2017-12-04T00:00:00Z")))
  val maDataEvent209 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1866), Price(1.1826), Price(1.1879), Price(1.1801), Value(0), Instant.parse("2017-12-05T00:00:00Z")))
  val maDataEvent210 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1825), Price(1.1796), Price(1.1849), Price(1.1780), Value(0), Instant.parse("2017-12-06T00:00:00Z")))
  val maDataEvent211 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1796), Price(1.1773), Price(1.1816), Price(1.1772), Value(0), Instant.parse("2017-12-07T00:00:00Z")))
  val maDataEvent212 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1772), Price(1.1764), Price(1.1778), Price(1.1729), Value(0), Instant.parse("2017-12-08T00:00:00Z")))
  val maDataEvent213 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1766), Price(1.1768), Price(1.1772), Price(1.1764), Value(0), Instant.parse("2017-12-10T00:00:00Z")))
  val maDataEvent214 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1768), Price(1.1768), Price(1.1812), Price(1.1761), Value(0), Instant.parse("2017-12-11T00:00:00Z")))
  val maDataEvent215 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1770), Price(1.1742), Price(1.1794), Price(1.1718), Value(0), Instant.parse("2017-12-12T00:00:00Z")))
  val maDataEvent216 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1742), Price(1.1827), Price(1.1833), Price(1.1730), Value(0), Instant.parse("2017-12-13T00:00:00Z")))
  val maDataEvent217 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1826), Price(1.1778), Price(1.1863), Price(1.1770), Value(0), Instant.parse("2017-12-14T00:00:00Z")))
  val maDataEvent218 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1778), Price(1.1754), Price(1.1814), Price(1.1751), Value(0), Instant.parse("2017-12-15T00:00:00Z")))
  val maDataEvent219 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1745), Price(1.1744), Price(1.1751), Price(1.1738), Value(0), Instant.parse("2017-12-17T00:00:00Z")))
  val maDataEvent220 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1753), Price(1.1782), Price(1.1835), Price(1.1736), Value(0), Instant.parse("2017-12-18T00:00:00Z")))
  val maDataEvent221 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1782), Price(1.1840), Price(1.1851), Price(1.1777), Value(0), Instant.parse("2017-12-19T00:00:00Z")))
  val maDataEvent222 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1840), Price(1.1871), Price(1.1903), Price(1.1829), Value(0), Instant.parse("2017-12-20T00:00:00Z")))
  val maDataEvent223 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1871), Price(1.1873), Price(1.1890), Price(1.1848), Value(0), Instant.parse("2017-12-21T00:00:00Z")))
  val maDataEvent224 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1875), Price(1.1858), Price(1.1877), Price(1.1816), Value(0), Instant.parse("2017-12-22T00:00:00Z")))
  val maDataEvent225 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1854), Price(1.1853), Price(1.1854), Price(1.1853), Value(0), Instant.parse("2017-12-24T00:00:00Z")))
  val maDataEvent226 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1850), Price(1.1869), Price(1.1882), Price(1.1842), Value(0), Instant.parse("2017-12-25T00:00:00Z")))
  val maDataEvent227 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1867), Price(1.1858), Price(1.1879), Price(1.1846), Value(0), Instant.parse("2017-12-26T00:00:00Z")))
  val maDataEvent228 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1859), Price(1.1888), Price(1.1912), Price(1.1855), Value(0), Instant.parse("2017-12-27T00:00:00Z")))
  val maDataEvent229 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1888), Price(1.1943), Price(1.1961), Price(1.1888), Value(0), Instant.parse("2017-12-28T00:00:00Z")))
  val maDataEvent230 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1943), Price(1.1998), Price(1.2030), Price(1.1937), Value(0), Instant.parse("2017-12-29T00:00:00Z")))
  val maDataEvent231 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2003), Price(1.2010), Price(1.2014), Price(1.1995), Value(0), Instant.parse("2018-01-01T00:00:00Z")))
  val maDataEvent232 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2013), Price(1.2059), Price(1.2084), Price(1.2003), Value(0), Instant.parse("2018-01-02T00:00:00Z")))
  val maDataEvent233 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2058), Price(1.2014), Price(1.2070), Price(1.2001), Value(0), Instant.parse("2018-01-03T00:00:00Z")))
  val maDataEvent234 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2015), Price(1.2068), Price(1.2090), Price(1.2004), Value(0), Instant.parse("2018-01-04T00:00:00Z")))
  val maDataEvent235 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2068), Price(1.2030), Price(1.2085), Price(1.2021), Value(0), Instant.parse("2018-01-05T00:00:00Z")))
  val maDataEvent236 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2028), Price(1.2037), Price(1.2039), Price(1.2027), Value(0), Instant.parse("2018-01-07T00:00:00Z")))
  val maDataEvent237 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2029), Price(1.1967), Price(1.2054), Price(1.1956), Value(0), Instant.parse("2018-01-08T00:00:00Z")))
  val maDataEvent238 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1967), Price(1.1937), Price(1.1977), Price(1.1916), Value(0), Instant.parse("2018-01-09T00:00:00Z")))
  val maDataEvent239 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1938), Price(1.1947), Price(1.2019), Price(1.1923), Value(0), Instant.parse("2018-01-10T00:00:00Z")))
  val maDataEvent240 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1946), Price(1.2032), Price(1.2060), Price(1.1928), Value(0), Instant.parse("2018-01-11T00:00:00Z")))
  val maDataEvent241 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2033), Price(1.2187), Price(1.2190), Price(1.2031), Value(0), Instant.parse("2018-01-12T00:00:00Z")))
  val maDataEvent242 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2195), Price(1.2192), Price(1.2212), Price(1.2191), Value(0), Instant.parse("2018-01-14T00:00:00Z")))
  val maDataEvent243 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2198), Price(1.2263), Price(1.2298), Price(1.2188), Value(0), Instant.parse("2018-01-15T00:00:00Z")))
  val maDataEvent244 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2264), Price(1.2260), Price(1.2283), Price(1.2195), Value(0), Instant.parse("2018-01-16T00:00:00Z")))
  val maDataEvent245 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2260), Price(1.2186), Price(1.2323), Price(1.2176), Value(0), Instant.parse("2018-01-17T00:00:00Z")))
  val maDataEvent246 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2186), Price(1.2239), Price(1.2266), Price(1.2165), Value(0), Instant.parse("2018-01-18T00:00:00Z")))
  val maDataEvent247 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2237), Price(1.2222), Price(1.2296), Price(1.2218), Value(0), Instant.parse("2018-01-19T00:00:00Z")))
  val maDataEvent248 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2270), Price(1.2256), Price(1.2273), Price(1.2235), Value(0), Instant.parse("2018-01-21T00:00:00Z")))
  val maDataEvent249 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2268), Price(1.2262), Price(1.2276), Price(1.2214), Value(0), Instant.parse("2018-01-22T00:00:00Z")))
  val maDataEvent250 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2263), Price(1.2299), Price(1.2308), Price(1.2223), Value(0), Instant.parse("2018-01-23T00:00:00Z")))
  val maDataEvent251 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2299), Price(1.2408), Price(1.2417), Price(1.2293), Value(0), Instant.parse("2018-01-24T00:00:00Z")))
  val maDataEvent252 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2407), Price(1.2397), Price(1.2538), Price(1.2363), Value(0), Instant.parse("2018-01-25T00:00:00Z")))
  val maDataEvent253 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2395), Price(1.2421), Price(1.2495), Price(1.2371), Value(0), Instant.parse("2018-01-26T00:00:00Z")))
  val maDataEvent254 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2427), Price(1.2431), Price(1.2431), Price(1.2414), Value(0), Instant.parse("2018-01-28T00:00:00Z")))
  val maDataEvent255 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2426), Price(1.2382), Price(1.2437), Price(1.2336), Value(0), Instant.parse("2018-01-29T00:00:00Z")))
  val maDataEvent256 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2383), Price(1.2402), Price(1.2455), Price(1.2335), Value(0), Instant.parse("2018-01-30T00:00:00Z")))
  val maDataEvent257 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2401), Price(1.2421), Price(1.2476), Price(1.2386), Value(0), Instant.parse("2018-01-31T00:00:00Z")))
  val maDataEvent258 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2414), Price(1.2510), Price(1.2524), Price(1.2386), Value(0), Instant.parse("2018-02-01T00:00:00Z")))
  val maDataEvent259 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2508), Price(1.2462), Price(1.2519), Price(1.2409), Value(0), Instant.parse("2018-02-02T00:00:00Z")))
  val maDataEvent260 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2437), Price(1.2447), Price(1.2455), Price(1.2424), Value(0), Instant.parse("2018-02-04T00:00:00Z")))
  val maDataEvent261 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2456), Price(1.2367), Price(1.2475), Price(1.2361), Value(0), Instant.parse("2018-02-05T00:00:00Z")))
  val maDataEvent262 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2367), Price(1.2377), Price(1.2434), Price(1.2314), Value(0), Instant.parse("2018-02-06T00:00:00Z")))
  val maDataEvent263 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2378), Price(1.2264), Price(1.2407), Price(1.2247), Value(0), Instant.parse("2018-02-07T00:00:00Z")))
  val maDataEvent264 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2264), Price(1.2247), Price(1.2297), Price(1.2212), Value(0), Instant.parse("2018-02-08T00:00:00Z")))
  val maDataEvent265 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2245), Price(1.2235), Price(1.2289), Price(1.2206), Value(0), Instant.parse("2018-02-09T00:00:00Z")))
  val maDataEvent266 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2249), Price(1.2252), Price(1.2252), Price(1.2243), Value(0), Instant.parse("2018-02-11T00:00:00Z")))
  val maDataEvent267 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2253), Price(1.2292), Price(1.2298), Price(1.2234), Value(0), Instant.parse("2018-02-12T00:00:00Z")))
  val maDataEvent268 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2293), Price(1.2352), Price(1.2373), Price(1.2285), Value(0), Instant.parse("2018-02-13T00:00:00Z")))
  val maDataEvent269 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2352), Price(1.2450), Price(1.2467), Price(1.2276), Value(0), Instant.parse("2018-02-14T00:00:00Z")))
  val maDataEvent270 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2449), Price(1.2506), Price(1.2511), Price(1.2447), Value(0), Instant.parse("2018-02-15T00:00:00Z")))
  val maDataEvent271 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2506), Price(1.2406), Price(1.2557), Price(1.2394), Value(0), Instant.parse("2018-02-16T00:00:00Z")))
  val maDataEvent272 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2404), Price(1.2412), Price(1.2420), Price(1.2403), Value(0), Instant.parse("2018-02-18T00:00:00Z")))
  val maDataEvent273 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2409), Price(1.2407), Price(1.2435), Price(1.2367), Value(0), Instant.parse("2018-02-19T00:00:00Z")))
  val maDataEvent274 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2409), Price(1.2338), Price(1.2414), Price(1.2320), Value(0), Instant.parse("2018-02-20T00:00:00Z")))
  val maDataEvent275 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2338), Price(1.2284), Price(1.2361), Price(1.2281), Value(0), Instant.parse("2018-02-21T00:00:00Z")))
  val maDataEvent276 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2283), Price(1.2331), Price(1.2354), Price(1.2260), Value(0), Instant.parse("2018-02-22T00:00:00Z")))
  val maDataEvent277 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2330), Price(1.2294), Price(1.2338), Price(1.2279), Value(0), Instant.parse("2018-02-23T00:00:00Z")))
  val maDataEvent278 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2299), Price(1.2318), Price(1.2356), Price(1.2278), Value(0), Instant.parse("2018-02-26T00:00:00Z")))
  val maDataEvent279 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2317), Price(1.2233), Price(1.2348), Price(1.2220), Value(0), Instant.parse("2018-02-27T00:00:00Z")))
  val maDataEvent280 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2233), Price(1.2195), Price(1.2243), Price(1.2188), Value(0), Instant.parse("2018-02-28T00:00:00Z")))
  val maDataEvent281 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2193), Price(1.2268), Price(1.2273), Price(1.2153), Value(0), Instant.parse("2018-03-01T00:00:00Z")))
  val maDataEvent282 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2266), Price(1.2317), Price(1.2336), Price(1.2250), Value(0), Instant.parse("2018-03-02T00:00:00Z")))
  val maDataEvent283 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2328), Price(1.2318), Price(1.2365), Price(1.2300), Value(0), Instant.parse("2018-03-04T00:00:00Z")))
  val maDataEvent284 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2323), Price(1.2336), Price(1.2366), Price(1.2268), Value(0), Instant.parse("2018-03-05T00:00:00Z")))
  val maDataEvent285 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2336), Price(1.2404), Price(1.2422), Price(1.2328), Value(0), Instant.parse("2018-03-06T00:00:00Z")))
  val maDataEvent286 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2403), Price(1.2414), Price(1.2445), Price(1.2384), Value(0), Instant.parse("2018-03-07T00:00:00Z")))
  val maDataEvent287 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2411), Price(1.2312), Price(1.2448), Price(1.2298), Value(0), Instant.parse("2018-03-08T00:00:00Z")))
  val maDataEvent288 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2312), Price(1.2307), Price(1.2336), Price(1.2273), Value(0), Instant.parse("2018-03-09T00:00:00Z")))
  val maDataEvent289 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2314), Price(1.2308), Price(1.2315), Price(1.2306), Value(0), Instant.parse("2018-03-11T00:00:00Z")))
  val maDataEvent290 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2311), Price(1.2334), Price(1.2347), Price(1.2291), Value(0), Instant.parse("2018-03-12T00:00:00Z")))
  val maDataEvent291 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2334), Price(1.2390), Price(1.2408), Price(1.2314), Value(0), Instant.parse("2018-03-13T00:00:00Z")))
  val maDataEvent292 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2390), Price(1.2367), Price(1.2414), Price(1.2347), Value(0), Instant.parse("2018-03-14T00:00:00Z")))
  val maDataEvent293 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2365), Price(1.2304), Price(1.2384), Price(1.2298), Value(0), Instant.parse("2018-03-15T00:00:00Z")))
  val maDataEvent294 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2305), Price(1.2289), Price(1.2338), Price(1.2261), Value(0), Instant.parse("2018-03-16T00:00:00Z")))
  val maDataEvent295 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2288), Price(1.2281), Price(1.2292), Price(1.2280), Value(0), Instant.parse("2018-03-18T00:00:00Z")))
  val maDataEvent296 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2284), Price(1.2335), Price(1.2361), Price(1.2258), Value(0), Instant.parse("2018-03-19T00:00:00Z")))
  val maDataEvent297 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2335), Price(1.2242), Price(1.2356), Price(1.2239), Value(0), Instant.parse("2018-03-20T00:00:00Z")))
  val maDataEvent298 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2242), Price(1.2338), Price(1.2351), Price(1.2241), Value(0), Instant.parse("2018-03-21T00:00:00Z")))
  val maDataEvent299 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2338), Price(1.2302), Price(1.2389), Price(1.2285), Value(0), Instant.parse("2018-03-22T00:00:00Z")))
  val maDataEvent300 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2302), Price(1.2353), Price(1.2375), Price(1.2301), Value(0), Instant.parse("2018-03-23T00:00:00Z")))
  val maDataEvent301 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2351), Price(1.2354), Price(1.2368), Price(1.2351), Value(0), Instant.parse("2018-03-25T00:00:00Z")))
  val maDataEvent302 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2348), Price(1.2443), Price(1.2462), Price(1.2340), Value(0), Instant.parse("2018-03-26T00:00:00Z")))
  val maDataEvent303 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2444), Price(1.2403), Price(1.2478), Price(1.2373), Value(0), Instant.parse("2018-03-27T00:00:00Z")))
  val maDataEvent304 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2403), Price(1.2308), Price(1.2424), Price(1.2300), Value(0), Instant.parse("2018-03-28T00:00:00Z")))
  val maDataEvent305 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2308), Price(1.2302), Price(1.2335), Price(1.2282), Value(0), Instant.parse("2018-03-29T00:00:00Z")))
  val maDataEvent306 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2301), Price(1.2323), Price(1.2331), Price(1.2293), Value(0), Instant.parse("2018-03-30T00:00:00Z")))
  val maDataEvent307 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2321), Price(1.2313), Price(1.2325), Price(1.2313), Value(0), Instant.parse("2018-04-01T00:00:00Z")))
  val maDataEvent308 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2323), Price(1.2302), Price(1.2346), Price(1.2282), Value(0), Instant.parse("2018-04-02T00:00:00Z")))
  val maDataEvent309 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2304), Price(1.2270), Price(1.2336), Price(1.2253), Value(0), Instant.parse("2018-04-03T00:00:00Z")))
  val maDataEvent310 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2269), Price(1.2278), Price(1.2315), Price(1.2256), Value(0), Instant.parse("2018-04-04T00:00:00Z")))
  val maDataEvent311 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2278), Price(1.2240), Price(1.2292), Price(1.2218), Value(0), Instant.parse("2018-04-05T00:00:00Z")))
  val maDataEvent312 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2240), Price(1.2283), Price(1.2292), Price(1.2214), Value(0), Instant.parse("2018-04-06T00:00:00Z")))
  val maDataEvent313 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2286), Price(1.2271), Price(1.2286), Price(1.2266), Value(0), Instant.parse("2018-04-08T00:00:00Z")))
  val maDataEvent314 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2279), Price(1.2321), Price(1.2331), Price(1.2261), Value(0), Instant.parse("2018-04-09T00:00:00Z")))
  val maDataEvent315 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2320), Price(1.2356), Price(1.2379), Price(1.2303), Value(0), Instant.parse("2018-04-10T00:00:00Z")))
  val maDataEvent316 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2357), Price(1.2367), Price(1.2397), Price(1.2347), Value(0), Instant.parse("2018-04-11T00:00:00Z")))
  val maDataEvent317 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2368), Price(1.2327), Price(1.2382), Price(1.2300), Value(0), Instant.parse("2018-04-12T00:00:00Z")))
  val maDataEvent318 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2327), Price(1.2331), Price(1.2348), Price(1.2307), Value(0), Instant.parse("2018-04-13T00:00:00Z")))
  val maDataEvent319 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2332), Price(1.2332), Price(1.2338), Price(1.2325), Value(0), Instant.parse("2018-04-15T00:00:00Z")))
  val maDataEvent320 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2330), Price(1.2380), Price(1.2395), Price(1.2325), Value(0), Instant.parse("2018-04-16T00:00:00Z")))
  val maDataEvent321 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2378), Price(1.2370), Price(1.2413), Price(1.2335), Value(0), Instant.parse("2018-04-17T00:00:00Z")))
  val maDataEvent322 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2369), Price(1.2373), Price(1.2398), Price(1.2343), Value(0), Instant.parse("2018-04-18T00:00:00Z")))
  val maDataEvent323 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2373), Price(1.2346), Price(1.2402), Price(1.2329), Value(0), Instant.parse("2018-04-19T00:00:00Z")))
  val maDataEvent324 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2346), Price(1.2288), Price(1.2354), Price(1.2250), Value(0), Instant.parse("2018-04-20T00:00:00Z")))
  val maDataEvent325 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2273), Price(1.2274), Price(1.2288), Price(1.2273), Value(0), Instant.parse("2018-04-22T00:00:00Z")))
  val maDataEvent326 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2289), Price(1.2209), Price(1.2292), Price(1.2198), Value(0), Instant.parse("2018-04-23T00:00:00Z")))
  val maDataEvent327 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2209), Price(1.2232), Price(1.2247), Price(1.2182), Value(0), Instant.parse("2018-04-24T00:00:00Z")))
  val maDataEvent328 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2232), Price(1.2161), Price(1.2240), Price(1.2160), Value(0), Instant.parse("2018-04-25T00:00:00Z")))
  val maDataEvent329 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2161), Price(1.2103), Price(1.2211), Price(1.2096), Value(0), Instant.parse("2018-04-26T00:00:00Z")))
  val maDataEvent330 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2105), Price(1.2130), Price(1.2135), Price(1.2055), Value(0), Instant.parse("2018-04-27T00:00:00Z")))
  val maDataEvent331 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2124), Price(1.2123), Price(1.2130), Price(1.2119), Value(0), Instant.parse("2018-04-29T00:00:00Z")))
  val maDataEvent332 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2125), Price(1.2079), Price(1.2141), Price(1.2064), Value(0), Instant.parse("2018-04-30T00:00:00Z")))
  val maDataEvent333 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.2076), Price(1.1993), Price(1.2086), Price(1.1981), Value(0), Instant.parse("2018-05-01T00:00:00Z")))
  val maDataEvent334 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1992), Price(1.1951), Price(1.2033), Price(1.1938), Value(0), Instant.parse("2018-05-02T00:00:00Z")))
  val maDataEvent335 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1951), Price(1.1988), Price(1.2009), Price(1.1946), Value(0), Instant.parse("2018-05-03T00:00:00Z")))
  val maDataEvent336 = MADataEvent(
    Candlestick(StockSymbol("EUR_USD"), Price(1.1989), Price(1.1961), Price(1.1995), Price(1.1911), Value(0), Instant.parse("2018-05-04T00:00:00Z")))

}
