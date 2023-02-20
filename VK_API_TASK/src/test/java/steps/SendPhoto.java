package steps;

import model.PhotoStepFields;
import model.TestUser;
import pojos.SavePhotoPojos;
import pojos.UploadedPhotoPojos;
import restApiUtils.ApiUtils;
import restApiUtils.RequestUploadPhoto;
import restApiUtils.ResponseSpecs;
import utils.DeserializeJSONToUploadedPhoto;
import utils.GenerateStringForPhoto;

public class SendPhoto {

    public static PhotoStepFields sendPhotoStep(TestUser user) {
        PhotoStepFields photoStepFields = new PhotoStepFields();
        String uploadServerUrl = ApiUtils.getWallPhotoUploadServer(ResponseSpecs.OK).getResponse().getUploadUrl();
        UploadedPhotoPojos uploadedPhotoPojos = DeserializeJSONToUploadedPhoto.getUploadedPhotoObject(
                RequestUploadPhoto.uploadPhotoToServer(uploadServerUrl));
        SavePhotoPojos savePhotoPojos = ApiUtils.saveUploadWallPhoto(uploadedPhotoPojos.getServer(),
                uploadedPhotoPojos.getPhoto(), uploadedPhotoPojos.getHash(), ResponseSpecs.OK);
        photoStepFields.setSavePhotoPojos(savePhotoPojos);
        String photo = GenerateStringForPhoto.getString(user.getOwnerId(), savePhotoPojos.getResponse().get(0).getId());
        photoStepFields.setPhoto(photo);
        return photoStepFields;
    }


}
