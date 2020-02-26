package gov.healthit.chpl.aqa.api.payLoad;

import gov.healthit.chpl.aqa.api.asserts.AnnoucementsAsserts;

public final class AnnouncementsPayload {

    private AnnouncementsPayload() {}

    public static String postPayload() {
        String payload = "{\n"
               + "  \"creationDate\": \"2020-02-04T15:29:32.175Z\",\n"
               + "  \"deleted\": false,\n"
               + "  \"endDate\": \"2021-02-04T15:29:32.175Z\",\n"
               + "  \"isPublic\": true,\n"
               + "  \"lastModifiedDate\": \"2020-02-04T15:29:32.175Z\",\n"
               + "  \"lastModifiedUser\": 0,\n"
               + "  \"startDate\": \"2020-02-04T15:29:32.175Z\",\n"
               + "  \"text\": \"API test annoucement\",\n"
               + "  \"title\": \"API test annoucement\"\n"
               + "}";
        return payload;
    }

    public static String putPayload() {
        int id = AnnoucementsAsserts.getPostAnnoucementId();
        String payload = "{\n"
               + "  \"creationDate\": \"2020-02-04T15:29:32.175Z\",\n"
               + "  \"id\": \"" + id + "\",\n"
               + "  \"deleted\": false,\n"
               + "  \"endDate\": \"2021-02-04T15:29:32.175Z\",\n"
               + "  \"isPublic\": true,\n"
               + "  \"lastModifiedDate\": \"2020-02-04T15:29:32.175Z\",\n"
               + "  \"lastModifiedUser\": 0,\n"
               + "  \"startDate\": \"2020-02-04T15:29:32.175Z\",\n"
               + "  \"text\": \"API test annoucement\",\n"
               + "  \"title\": \"Updated Title- API test annoucement\"\n"
               + "}";
        return payload;
    }
}
