package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.UploadedPhotoPojos;

import java.io.IOException;

public class DeserializeJSONToUploadedPhoto {

    public static UploadedPhotoPojos getUploadedPhotoObject(String stringJSON) {
        ObjectMapper mapper = new ObjectMapper();
        UploadedPhotoPojos uploadedPhoto = null;
        try {
            uploadedPhoto = mapper.readValue(stringJSON, UploadedPhotoPojos.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadedPhoto;
    }
}
