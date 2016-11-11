/*package cn.ifxcode.jsonDateFormat;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.springframework.stereotype.Component;

@Component("customObjectMapper")
public class CustomObjectMapper extends ObjectMapper{

	public CustomObjectMapper(){
		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){

			@Override
			public void serialize(Date value,JsonGenerator jsonGenerator,
					SerializerProvider provider) throws IOException,
					JsonProcessingException {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonGenerator.writeString(format.format(value));
			}
		});
		this.setSerializerFactory(factory);
	}
}
*/