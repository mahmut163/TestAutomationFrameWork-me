package cubecartrestassuredapi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadUtility {
    public static String getCategoryPayload(String catDescription,String catName,int catImage,int catParentId,int status){
        long timeStamp = System.currentTimeMillis();
        String payload= null;
        CategoryPayload categoryPayload = new CategoryPayload(catDescription+timeStamp,catImage,
                catName+timeStamp,catParentId,status);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            payload = objectMapper.writeValueAsString(categoryPayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payload;
    }
//    public class PayloadUtility {
//        public static String getCategory(String catname,String catdescription, int catimage,  int status) {
//            long timestep = System.nanoTime();
//            String payload = null;
//            CategoryPayload categoryPayload = new CategoryPayload(catname,catdescription,catimage,status);
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                payload = objectMapper.writeValueAsString(categoryPayload);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            return payload;
//        }
}

