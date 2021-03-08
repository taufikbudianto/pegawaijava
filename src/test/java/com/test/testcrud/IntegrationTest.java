package com.test.testcrud;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.TestcrudApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestcrudApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
    @Test
    public void insertData() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        Nama Dan Alamat Blank
        Map<String, Object> mp = new HashMap<>();
        mp.put("nama","");
        mp.put("alamat","");
        HttpEntity<Map<String, Object>> requestData =
                new HttpEntity<Map<String, Object>>(mp, headers);
        ResponseEntity<String> responseEntity =this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/save",requestData, String.class);
        assertEquals(responseEntity.getStatusCodeValue(),400);


//        Nama Blank
        mp = new HashMap<>();
        mp.put("nama","Test");
        mp.put("alamat","");
        requestData = new HttpEntity<Map<String, Object>>(mp, headers);
        responseEntity =this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/save",requestData, String.class);
        assertEquals(responseEntity.getStatusCodeValue(),400);

        //        Alamat Blank
        mp = new HashMap<>();
        mp.put("nama","Test");
        mp.put("alamat","");
        requestData = new HttpEntity<Map<String, Object>>(mp, headers);
        responseEntity =this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/save",requestData, String.class);
        assertEquals(responseEntity.getStatusCodeValue(),400);

        //        sukses
        mp = new HashMap<>();
        mp.put("nama","Test");
        mp.put("alamat","JALAN JALAN");
        requestData = new HttpEntity<Map<String, Object>>(mp, headers);
        responseEntity =this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/save",requestData, String.class);
        assertEquals(responseEntity.getBody(),"Sukses");
    }
	@Test
	 public void retrieveData() throws Exception {
//      Response For Data Found
		ResponseEntity<String> responseEntity =this.restTemplate
				.getForEntity("http://localhost:" + port + "/api/getbyuserid?userid=1", String.class);
		assertEquals(responseEntity.getStatusCodeValue(),200);
//      Response For Data Not Found
        responseEntity =this.restTemplate
                .getForEntity("http://localhost:" + port + "/api/getbyuserid?userid=1000", String.class);
        assertEquals(responseEntity.getStatusCodeValue(),400);

        //      Response For UserId not numeric
        responseEntity =this.restTemplate
                .getForEntity("http://localhost:" + port + "/api/getbyuserid?userid=1fsf", String.class);
        assertEquals(responseEntity.getStatusCodeValue(),400);
	}
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
