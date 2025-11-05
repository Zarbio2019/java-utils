package org;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.enums.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;

/**
 * Java Bean Mappings
 * https://www.baeldung.com/java-performance-mapping-frameworks
 *
 * automatic mapping, more used/better:
 *   1. JMapper
 *   2. MapStruct
 *   3. ModelMapper
 *
 * naming examples:
 * mapInput(), mapOutput()
 */
public class MappingUtil {

	public static void main(String[] args) {

        System.out.println("MappingUtil");

        // Build Map<String, Object> from String parameters
        System.out.println("Build Map<String, Object> from String parameters");

        Document doc = new Document("DNI", "12345678");
        Client client = new Client("Ana", "engineer");
        client.setId("1");

        Client client2 = new Client("Willy", "qa");
        client2.setId("2");

        ClientBO clientBO = new ClientBO();
        clientBO.setName("Alvy");
        clientBO.setFullName("Ellie");
        clientBO.setDescription("bot");

        List<Client> listClients = new ArrayList<>();
        listClients.add(client);
        listClients.add(client2);

        Map<String, Object> map = getMapParams(doc.getNumber(), client.getName());
        System.out.println("getMapParams: " + map);

        // Build Map<String, Object> from Object/Bean parameters
        System.out.println("\nBuild Map<String, Object> from Object/Bean parameters");

        Map<String, Object> mapObj = getMapParamsObj(doc, client);
        System.out.println("getMapParamsObj: " + mapObj);

        /**
         Get List of all fields of the parent List

        From:
            [
                Client{..., details='Details{id='1111'}'},
                Client{..., details='Details{id='2222'}'}
            ]
        To:
            [1111, 2222]
         */
        System.out.println("\nGet List of all fields of the parent List");

        List<Client> clientListN = new ArrayList<>();
        Client clientN = new Client();
        Document docN = new Document();
        Details detailsN = new Details();
        detailsN.setId("1111");
        docN.setDetails(detailsN);
        clientN.setDocument(docN);
        clientListN.add(clientN);

        Client clientN2 = new Client();
        Document docN2 = new Document();
        Details detailsN2 = new Details();
        detailsN2.setId("2222");
        docN2.setDetails(detailsN2);
        clientN2.setDocument(docN2);
        clientListN.add(clientN2);

        System.out.println("clientListN: " + clientListN);

        List<String> detailsId = clientListN.stream()
                .map(Client::getDocument)
                .map(Document::getDetails)
                .map(Details::getId)
                .collect(Collectors.toList());

        System.out.println("detailsId: " + detailsId); // [1111, 2222]

        // Map from Set Object to List<Map>
        System.out.println("\nMap from Set Object to List<Map>");

        Set<Client> setClients = new HashSet<>();
        setClients.add(new Client("name1", "description1"));
        setClients.add(new Client("name2", "description2"));
        setClients.add(new Client("name3", "description3"));
        List<Map<String, Object>> listMapClients = new MappingUtil().mapClients(setClients);
        System.out.println("listMapClients: " + listMapClients); // [{name=name1, description=description1}, {name=name3, description=description3}, {name=name2, description=description2}]

        // Map from List Object to Map<String, String>
        System.out.println("\nMap from List Object to Map<String, String>");
        Map<String, String> mapClients = new MappingUtil().mapClientsListtoMap(listClients);
        System.out.println("mapClients: " + mapClients); // {1=Ana, 2=Willy}

        // Map from List Object to Map<String, Object>
        System.out.println("\nMap from List Object to Map<String, Object>");
        Map<String, Client> mapClients2 = new MappingUtil().mapClientsListtoMap2(listClients);
        System.out.println("mapClients2: " + mapClients2); // {1=Client{id='1'name='Ana', description='engineer', document=null}, 2=Client{id='2'name='Willy', description='qa', document=null}}

        // Map from Map<String, String> to List Object
        System.out.println("\nMap from Map<String, String> to List Object");
        List<Client> listClients3 = new MappingUtil().mapClientsMaptoList(mapClients);
        System.out.println("listClients3: " + listClients3); // {1=Ana, 2=Willy}

        // Map DTO/entity to another DTO/entity manually
        System.out.println("\nMap DTO/entity to another DTO/entity manually");

        Client clientOut = mapClientManual(clientBO);
        System.out.println("clientOut: " + clientOut);

        // Map DTO/entity to another DTO/entity using Mapstruct
        System.out.println("\nMap DTO/entity to another DTO/entity using Mapstruct");
        clientOut = mapClientMapstruct(clientBO);
        System.out.println("clientOut: " + clientOut);

        // Map DTO/entity to another DTO/entity using ModelMapper (better)
        System.out.println("\nMap DTO/entity to another DTO/entity using ModelMapper (better)");
        clientOut = mapClientModelMapper(clientBO);
        System.out.println("clientOut: " + clientOut);

        // Map list DTO/entity to another list DTO/entity using lambda
        System.out.println("\nMap list DTO/entity to another list DTO/entity using lambda");

        List<ClientBO> clientsBO = new ArrayList<>();
        ClientBO clientBO1 = new ClientBO();
        clientBO1.setName("name1");
        clientBO1.setDescription("description1");
        clientsBO.add(clientBO1);

        ClientBO clientBO2 = new ClientBO();
        clientBO2.setName("name2");
        clientBO2.setDescription("description2");
        clientsBO.add(clientBO2);

        List<Client> listClients2 = mapClients(clientsBO);
        System.out.println("listClients2: " + listClients2);

        // Map list DTO/entity to another list DTO/entity using lambda and super
        System.out.println("\nMap list DTO/entity to another list DTO/entity using lambda and super");

        List<ClientDTO2> listClientsDTO2 = mapClients2(clientsBO);
        System.out.println("listClientsDTO2: " + listClientsDTO2);

        // Map list DTO/entity to another list DTO/entity manually
        System.out.println("\nMap list DTO/entity to another list DTO/entity manually");

        listClients2 = mapClientsManual(clientsBO);
        System.out.println("listClients2: " + listClients2);

        // Map DTO with list DTO/entity to another list DTO/entity manually
        System.out.println("\nMap DTO with list DTO/entity to another list DTO/entity manually");
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientsBO(clientsBO);
        List<Client> listClient3 = mapClientsManual2(clientDTO);
        System.out.println("listClient3: " + listClient3);

        // Remove items of a List that not contains from a String array
        System.out.println("\nRemove items of a List that not contains from a String array");
        String[] ids = { "1", "2", "3", "4", "5"};

        List<Client> listClients4 = new ArrayList<>();
        listClients4.add(new Client("6", "Ana", "engineer"));
        listClients4.add(new Client("3", "Tom", "qa"));

        listClients4.removeIf(x -> !Arrays.asList(ids).contains(x.getId()));
        System.out.println("listClients4: " + listClients4); // [Client{id='3'name='Tom', description='qa', document=null}]

        // Remove/Filter of a List that not contains a specific value
        System.out.println("\nRemove/Filter of a List that not contains a specific value");
        final String id = "4";

        List<Client> listClients5 = new ArrayList<>();
        listClients5.add(new Client("1", "Ana", "engineer", new Document("DNI", "1111")));
        listClients5.add(new Client("4", "Tom1", "qa1", new Document("RUC", "2222")));
        listClients5.add(new Client("4", "Tom2", "qa2", new Document("DNI", "3333")));

        Client client3 = listClients5.stream()
                .filter(i -> i.getId().equals(id)) // id must be final
                .findFirst()
                .orElse(null);
        System.out.println("client3: " + client3); // {id='4'name='Tom1', description='qa1', document=null}

        // Remove/Filter of a List by document type and number
        Optional<Client> optClient = listClients5.stream()
                .filter(d -> d.getDocument() != null && d.getDocument().getType() == DocumentType.DNI.toString()) // filter by document type
                .filter(d -> d.getDocument().getNumber().equals("3333")) // filter by document number
                .findFirst();

        if(!optClient.isPresent()) {
//            throw new Exception("No client");
        }
        System.out.println("optClient: " + optClient.get()); // id='4'name='Tom2', description='qa2', document=Document{type='DNI', number='3333'}

        // Map list DTO/entity to another list DTO/entity filtering data and setting data
        System.out.println("\nMap list DTO/entity to another list DTO/entity filtering data and setting data");
        List<ClientBO> listClientBO1 = new ArrayList<>();

        ClientBO clientBO3 = new ClientBO();
        clientBO3.setAge("1");
        listClientBO1.add(clientBO3);

        ClientBO clientBO4 = new ClientBO();
        clientBO4.setAge("2");
        listClientBO1.add(clientBO4);

        ClientBO clientBO5 = new ClientBO();
        clientBO5.setAge("3");
        listClientBO1.add(clientBO5);

        List<Client> listClient = mapClientsFilter(listClientBO1);
        System.out.println("listClient: " + listClient); // Client{id='null'name='null', description='null', document=Document{type='DNI', number='null'}}, Client{id='null'name='null', description='null', document=Document{type='DNI', number='null'}}

        /**
         * output:
         *  data = "BBVA,client1,client2;IBK,client3,client4,client5"
         *  filter = "IBK"
         *
         * output: "client3,client4,client5";
         */
        String data = "BBVA,client1,client2;IBK,client3,client4,client5";
        String filters = filterClientByGroup(data, "IBK");
        System.out.println("filters: " + filters);

        /**
         * Convert: "BBVA,client1,client2;IBK,client3,client4,client5"
         * To Map/Object:
         *    {
         * 		"BBVA": {
         * 			"client1": "OK",
         * 			"client2": "OK"
         *        },
         * 		"IBK": {
         * 			"client3": "OK",
         * 			"client4": "OK",
         * 			"client5": "OK"
         *        }
         *    }
         */
        data = "BBVA,client1,client2;IBK,client3,client4,client5";
        Map<String,Map<String,String>> map2 = mapClientByGroup(data);
        System.out.println("map2: " + map2);

        /**
         * Convert: "11,name1,description1;22,name2,description2;11,name3,description3"
         * To List:
         *    [
         *      {"11", "name1", "description1"},
         *      {"22", "name2", "description2"},
         *      {"11", "name3", "description3"}
         *    ]
         */
        data = "11,name1,description1;22,name2,description2;11,name3,description3";
        List<Client> clientList = listClientByGroup(data, "11");
        System.out.println("clientList: " + clientList);

        // Vaiidate null, filter by DNI, set value
        Client client4 = new Client();
        Description des = new Description();
        Details details = new Details();
        details.setId(DocumentType.DNI.name());
        details.setValue("12345678");
        des.setDetails(Arrays.asList(details));
        client4.setDes(des);

        Details details2 = new Details();

        Optional.ofNullable(client4)
                .map(Client::getDes)
                .map(Description::getDetails)
                .filter(d -> !d.isEmpty())
                .flatMap(dataList -> dataList.stream()
                        .filter(d -> d.getId().equals(DocumentType.DNI.name()))
                        .findFirst())
                .ifPresent(r -> details2.setValue(r.getValue()));
        System.out.println("details2: " + details2);

        //
//        "addressComponents": [
//        {
//            "componentTypes": [
//            "UBIGEO"
//              ],
//            "code": "0101041"
//        },
//        {
//            "code": "COUNTRY",
//                "name": "Peru"
//        },
//        {
//            "code": "CITY",
//                "name": "Lima"
//        },
//        {
//            "code": "ZIPCODE",
//                "name": "1234"
//        }
//        ]
//
//        private Address mapAddress(List<AddressComponentDTO> addressComponents) {
//
//            Map<String, List<String>> addressComponentsMap = addressComponents.stream()
//                    .collect(Collectors.toMap(
//                            component -> component.getComponentTypes().get(0),
//                            component -> {
//                                List<String> valueList = new ArrayList<>();
//                                if (!Objects.isNull(component.getCode())) {
//                                    valueList.add(component.getCode());
//
//                                    if (!Objects.isNull(component.getName())) {
//                                        valueList.add(component.getName());
//                                    }
//                                }
//                                return valueList;
//                            }
//                    ));
//
//            Address address = new Address();
//            address.setCountry(mapCountry(addressComponentsMap.get("COUNTRY")));
//            address.setCity(mapCity(addressComponentsMap.get("CITY")));
//            address.setZipCode(mapZipCode(addressComponentsMap.get("ZIPCODE")));
//
//            return address;
//        }
    }

	/**************************************************/

	/**
	 * Build Map<String, Object> from String parameters
	 */
	public static Map<String, Object> getMapParams(String param1, String param2) {
		Map<String, Object> params = new HashMap<>();
		params.put("param_1", param1);
		params.put("param_2", param2);
		return params;
	}

    /**
     * Build Map<String, Object> from Object/Bean parameters
     */
    public static Map<String, Object> getMapParamsObj(Document doc, Client client) {
        Map<String, Object> params = new HashMap<>();
        params.put("document", doc);
        params.put("client", client);

        Map<String, Object> job = new HashMap<>();
        job.put("job_tittle", "Engineer");
        job.put("job_description", "Developing code");

        params.put("job", job);
        return params;
    }

    private Map<String, Object> mapClient(Client client) {
        final Map<String, Object> map = new HashMap<>();
        map.put("name", client.getName());
        map.put("description", client.getDescription());
        return map;
    }

    /**
     * Map from Set Object to List<Map>
     */
    public List<Map<String, Object>> mapClients(final Set<Client> clients) {
        return clients.stream()
                      .map(this::mapClient)
                      .collect(Collectors.toList());
    }

    /**
     * Map from List Object to Map<String, String>
     */
    public Map<String, String> mapClientsListtoMap(List<Client> clients) {

        Map<String, String> mapClients = clients.stream()
                .map(client -> new AbstractMap.SimpleEntry<>(client.getId(), client.getName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return mapClients;
    }

    /**
     * Map from List Object to Map<String, Object>
     */
    public Map<String, Client> mapClientsListtoMap2(List<Client> clients) {

        Map<String, Client> mapClients = clients.stream()
                .collect(Collectors.toMap(Client::getId, Function.identity()));

        return mapClients;
    }

    /**
     * Map from Map<String, String> to List Object
     */
    public List<Client> mapClientsMaptoList(Map<String, String> mapclient) {

        return mapclient.entrySet().stream().map(x -> {
           Client client = new Client();
           client.setId(x.getKey());
           client.setName(x.getValue());
           return client;
        }).collect(Collectors.toList());
    }

    /**
     * Map DTO/entity to another DTO/entity manually
     */
    public static Client mapClientManual(ClientBO clientIn) {
        if(clientIn == null)
            return null;

        Client clientOut = new Client();
        clientOut.setName(clientIn.getName());
        clientOut.setDescription(clientIn.getDescription());

        return clientOut;
    }

    /**
     * Map DTO/entity to another DTO/entity using Mapstruct
     *
     * Mapstruct is used at compile time and with Dependency Injection, within the context of a service or a controller class.
     * Isn't a common practice to use Mapstruct inside a static void main() method.
     */
    public static Client mapClientMapstruct(ClientBO clientIn) {
        // interface ClientMapper, to generate the implementation class, run: mvn clean install

        // convert ClientBO to Client
        Client client = ClientMapper.INSTANCE.toEntity(clientIn);
        System.out.println("Client " + client);

        // convert Client to ClientBO
        ClientBO newclientBO = ClientMapper.INSTANCE.toDTO(client);
        System.out.println("newclientBO " + newclientBO);

        return client;
    }

//    @Test
//    public void mapClientMapstructTest() {
//        // given
//        ClientBO clientBO = new ClientBO();
//        clientBO.setName("Alvy");
//        clientBO.setFullName("Ellie");
//        clientBO.setDescription("bot");
//
//        // when
//        Client client = ClientMapper.INSTANCE.toEntity(clientBO);
//
//        // then
//        assertThat(client).isNotNull();
//        assertThat(client.getName()).isEqualTo(clientBO.getName());
//    }

//    ref:
//    https://www.baeldung.com/mapstruct
//    https://mapstruct.org/
//    https://mvnrepository.com/search?q=org.mapstruct
//    install: https://mapstruct.org/documentation/installation/
//    Mapstruct expressions: https://mapstruct.org/documentation/stable/reference/html/#expressions

// sample:
// ref: java\spring\springboot\projects\web\reddit clone\backend\spring-reddit-clone-master\src\main\java\com\programming\techie\springredditclone\service\SubredditService.java

//List<Subreddit> result = new ArrayList<>();
//result.stream()
//        .map(subredditMapper::mapSubredditToDto) // map using Mapstruct
//        .collect(toList());

//- SubredditMapper.java:
//
//import org.mapstruct.InheritInverseConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring") // annotation for Mapstruct Mapper
//public interface SubredditMapper {
//
//// map Subreddit to SubredditDto
//@Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
//SubredditDto mapSubredditToDto(Subreddit subreddit);
//
//default Integer mapPosts(List<Post> numberOfPosts) {
//  return numberOfPosts.size();
//}
//
//// map SubredditDto to Subreddit
//@InheritInverseConfiguration
//@Mapping(target = "posts", ignore = true)
//  Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
//}
//
//- Subreddit.java:
//package com.programming.techie.springredditclone.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.Instant;
//import java.util.List;
//
//import static jakarta.persistence.FetchType.LAZY;
//import static jakarta.persistence.GenerationType.SEQUENCE;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Builder
//public class Subreddit {
//    @Id
//    @GeneratedValue(strategy = SEQUENCE)
//    private Long id;
//    @NotBlank(message = "Community name is required")
//    private String name;
//    @NotBlank(message = "Description is required")
//    private String description;
//    @OneToMany(fetch = LAZY)
//    private List<Post> posts;
//}
//
//- SubredditDto.java
//package com.programming.techie.springredditclone.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class SubredditDto {
//        private Long id;
//        @NotBlank(message = "Community name is required")
//        private String name;
//        @NotBlank(message = "Description is required")
//        private String description;
//        private Integer numberOfPosts;
//}

//- Installing Mapstruct: add in pom.xml file
//..
//// In the Properties section
//<properties>
//    <org.mapstruct.version>1.3.1.Final</org.mapstruct.version>
//</properties>
//            ...
//// In the dependencies Section
//<dependencies>
//    <dependency>
//        <groupId>org.mapstruct</groupId>
//        <artifactId>mapstruct</artifactId>
//    <version>${org.mapstruct.version}</version>
//    </dependency>
//</dependencies>
//            ...
// In the build Section
//<build>
//    <plugins>
//        <plugin>
//            <groupId>org.apache.maven.plugins</groupId>
//            <artifactId>maven-compiler-plugin</artifactId>
//            <version>3.5.1</version> <!-- or newer version -->
//            <configuration>
//                <source>1.8</source> <!-- depending on your project -->
//                <target>1.8</target> <!-- depending on your project -->
//                <annotationProcessorPaths>
//                    <path>
//                        <groupId>org.mapstruct</groupId>
//                        <artifactId>mapstruct-processor</artifactId>
//    <version>${org.mapstruct.version}</version>
//                    </path>
//                     <path>
//                            <groupId>org.projectlombok</groupId>
//                            <artifactId>lombok</artifactId>
//                            <version>1.18.8</version>
//                     </path>
//                </annotationProcessorPaths>
//            </configuration>
//        </plugin>
//    </plugins>
//</build>

    /**
     * Map DTO/entity to another DTO/entity using ModelMapper
     */
    public static Client mapClientModelMapper(ClientBO clientIn) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(clientIn, Client.class);
    }
//    import org.modelmapper.ModelMapper
//
//    - add dependency:
//    <dependency>
//        <groupId>org.modelmapper</groupId>
//        <artifactId>modelmapper</artifactId>
//        <version>3.2.0</version>
//    </dependency>
//
//    ref:
//    https://www.baeldung.com/java-modelmapper
//    sample: from entity UserDto to UserRest, see: java\\unit testing\\springboot\\UsersServiceSpringBoot

    /**
     * Map DTO/entity to another DTO/entity manually using @Builder from package lombok used in Spring

     List<Subreddit> result = new ArrayList<>();
     result.stream()
     .map(this::mapToDto)
     .map(subredditMapper::mapSubredditToDto)
     .collect(toList());

     private SubredditDto mapToDto(Subreddit subreddit) {
     return SubredditDto.builder()
     .name(subreddit.getName())
     .id(subreddit.getId())
     .numberOfPosts(subreddit.getPosts().size())
     .build();
     }

     - Subreddit.java:
     package com.programming.techie.springredditclone.model;

     import jakarta.persistence.*;
     import jakarta.validation.constraints.NotBlank;
     import lombok.AllArgsConstructor;
     import lombok.Builder;
     import lombok.Data;
     import lombok.NoArgsConstructor;

     import java.time.Instant;
     import java.util.List;

     import static jakarta.persistence.FetchType.LAZY;
     import static jakarta.persistence.GenerationType.SEQUENCE;

     @Data
     @AllArgsConstructor
     @NoArgsConstructor
     @Entity
     @Builder
     public class Subreddit {
     @Id
     @GeneratedValue(strategy = SEQUENCE)
     private Long id;
     @NotBlank(message = "Community name is required")
     private String name;
     @NotBlank(message = "Description is required")
     private String description;
     @OneToMany(fetch = LAZY)
     private List<Post> posts;
     }

     - SubredditDto.java
     package com.programming.techie.springredditclone.dto;

     import jakarta.validation.constraints.NotBlank;
     import lombok.AllArgsConstructor;
     import lombok.Builder;
     import lombok.Data;
     import lombok.NoArgsConstructor;

     @Data
     @AllArgsConstructor
     @NoArgsConstructor
     @Builder
     public class SubredditDto {
     private Long id;
     @NotBlank(message = "Community name is required")
     private String name;
     @NotBlank(message = "Description is required")
     private String description;
     private Integer numberOfPosts;
     }

     - add lombok dependency:
     <dependency>
     <groupId>org.projectlombok</groupId>
     <artifactId>lombok</artifactId>
     <optional>true</optional>
     </dependency>

     ref: java\spring\springboot\projects\web\reddit clone\backend\spring-reddit-clone-master\src\main\java\com\programming\techie\springredditclone\service\SubredditService.java
     */

    /**
     * Map list DTO/entity to another list DTO/entity using lambda
     */
    public static List<Client> mapClients(List<ClientBO> clientsBO) {
        return clientsBO.stream()
                          .map(Client::new)// with method reference (better), new = Constructor reference
//                          .map(i -> new Client(i))
                          .collect(Collectors.toList());
    }

    /**
     * Map list DTO/entity to another list DTO/entity using lambda and super
     */
    public static List<ClientDTO2> mapClients2(List<ClientBO> clientsBO) {
        return clientsBO.stream()
                .map(ClientDTO2::new) // with method reference (better), new = Constructor reference
//                          .map(i -> new ClientDTO2(i))
                .collect(Collectors.toList());
    }

    /**
     * Map list DTO/entity to another list DTO/entity manually
     */
    public static List<Client> mapClientsManual(List<ClientBO> clientsBO) {
        return clientsBO.stream()
                        .map(MappingUtil::mapClientManual)
//                        .map(this::mapClientManual) // this is used for non-static method (mapClientManual)
                        .collect(Collectors.toList());
    }
    // ref: PPGSRP20IMPL\CustomerMapper.java

    /**
     * Map DTO with list DTO/entity to another list DTO/entity manually
     */
    public static List<Client> mapClientsManual2(ClientDTO clientsDTO) {
        List<Client> clients = new ArrayList<>();

        clientsDTO.getClientsBO().forEach(clientBO -> {
            Client client = new Client();
            client.setId(clientBO.getId());
            client.setName(clientBO.getName());
            clients.add(client);
        });

        return clients;
    }

    /**
     * Map list DTO/entity to another list DTO/entity filtering data and setting data
     */
    public static List<Client> mapClientsFilter(List<ClientBO> clientsBO) {
        List<Client> listClients = new ArrayList<>();

        clientsBO.stream()
                .filter(age -> Integer.parseInt(age.getAge()) > 1)
                .forEach(client -> {
                    Client clientOut = new Client();
                    clientOut.setDocument(new Document());
                    clientOut.getDocument().setType(DocumentType.DNI.toString());
                    listClients.add(clientOut);
                });

        return listClients;
    }

    /**
     * input:
     *  data = "BBVA,client1,client2;IBK,client3,client4,client5"
     *  filter = "IBK"
     *
     * output: "client3,client4,client5";
     */
    private static String filterClientByGroup(String data, String filter) {
        String[] groupList = data.split(Constants.SEMICOLON);

        for(String group : groupList) {
            String header = group.split(Constants.COMMA)[0];

            if(header.equals(filter)) {
                return group.substring(header.length()+1);
            }
        }
        return null;
    }

    /**
     * Convert: "BBVA,client1,client2;IBK,client3,client4,client5"
     * To:
     *    {
     * 		"BBVA": {
     * 			"client1": "OK",
     * 			"client2": "OK"
     *        },
     * 		"IBK": {
     * 			"client3": "OK",
     * 			"client4": "OK",
     * 			"client5": "OK"
     *        }
     *    }
     */
    private static Map<String, Map<String, String>> mapClientByGroup(String data) {
        Map<String, Map<String, String>> map = new HashMap<>();
        String[] groupList = data.split(Constants.SEMICOLON);
        // BBVA,client1,client2
        // IBK,client3,client4,client5

        for(String group : groupList) {

            Map<String, String> clientMap = new HashMap<>();

            String[] clientList = group.split(Constants.COMMA);
            String groupName = clientList[0];

            for(int i = 1; i< clientList.length; i++) {
                clientMap.put(clientList[i], "OK");
            }

            map.put(groupName, clientMap);
        }
        return map;
    }

    /**
     * Convert: "11,name1,description1;22,name2,description2;11,name3,description3"
     * To List:
     *    [
     *      {"11", "name1", "description1"},
     *      {"22", "name2", "description2"},
     *      {"11", "name3", "description3"}
     *    ]
     */
    private static List<Client> listClientByGroup(String data, String id) {
        List<Client> clientList = new ArrayList<>();
        String[] groupList = data.split(Constants.SEMICOLON);

        for(String group : groupList) {

            Map<String, String> clientMap = new HashMap<>();

            String[] clientArr = group.split(Constants.COMMA);

            if(id.equals(clientArr[0])) {
                Client client = new Client(clientArr[0],clientArr[1],clientArr[2]);
                clientList.add(client);
            }
        }
        return clientList;
    }
}

class Document {
    private String type;
    private String number;
    private Details details;

    public Document() {}

    public Document(String type, String number) {
        this.type = type;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Document{");
        sb.append("type='").append(type).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", details='").append(details).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

class Client {

    private String id;
    private String name;
    private String description;
    private Document document;
    private Description des;

    public Client() {
//        super();
    }

    public Client(ClientBO clientBO) {
//        super();
        this.name = clientBO.getName();
        this.description = clientBO.getDescription();
    }

    public Client(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Client(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Client(String id, String name, String description, Document document) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.document = document;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Description getDes() {
        return des;
    }

    public void setDes(Description des) {
        this.des = des;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id='").append(id).append('\'');
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", document=").append(document);
        sb.append(", des=").append(des);
        sb.append('}');
        return sb.toString();
    }
}

class ClientBO extends Client {
    private String fullName;
    private String age;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

class ClientDTO {
    private List<ClientBO> clientsBO;

    public List<ClientBO> getClientsBO() {
        return clientsBO;
    }

    public void setClientsBO(List<ClientBO> clientsBO) {
        this.clientsBO = clientsBO;
    }
}

class ClientDTO2 extends Client {

    public ClientDTO2(ClientBO clientBO) {
        super(clientBO.getName(), clientBO.getDescription()); // go Client
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientDTO2{");
        sb.append("name='").append(this.getName()).append('\'');
        sb.append(", description='").append(this.getDescription()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

class Description {

    List<Details> details;

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Description{");
        sb.append("details=").append(details);
        sb.append('}');
        return sb.toString();
    }
}

class Details {

    private String id;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Details{");
        sb.append("id='").append(id).append('\'');
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

// Mapper interface, Mapstruct
@Mapper
interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class); // generate implementation of interface

    // convert ClientBO to Client
    @Mapping(source = "fullName", target = "name")
    Client toEntity(ClientBO clientBO);

    // convert Client to ClientBO
    @Mapping(source = "name", target = "fullName")
    ClientBO toDTO(Client clientBO);
}