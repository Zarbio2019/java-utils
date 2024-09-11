//package org;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
//public class MappingUtilTest {
//
//    @Autowired
//    SimpleSourceDestinationMapper simpleSourceDestinationMapper;
//
//    @Test
//    public void givenSourceToDestination_whenMaps_thenCorrect() {
//        SimpleSource simpleSource = new SimpleSource();
//        simpleSource.setName("SourceName");
//        simpleSource.setDescription("SourceDescription");
//
//        SimpleDestination destination = simpleSourceDestinationMapper.sourceToDestination(simpleSource);
//
//        assertEquals(simpleSource.getName(), destination.getName());
//        assertEquals(simpleSource.getDescription(), destination.getDescription());
//    }
//
//    @Test
//    public void givenDestinationToSource_whenMaps_thenCorrect() {
//        SimpleDestination destination = new SimpleDestination();
//        destination.setName("DestinationName");
//        destination.setDescription("DestinationDescription");
//
//        SimpleSource source = simpleSourceDestinationMapper.destinationToSource(destination);
//
//        assertEquals(destination.getName(), source.getName());
//        assertEquals(destination.getDescription(), source.getDescription());
//    }
//
//}