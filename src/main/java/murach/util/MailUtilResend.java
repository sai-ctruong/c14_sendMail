package murach.util;

import okhttp3.*;
import org.json.JSONObject;

public class MailUtilResend {

    private static final String API_URL = "https://api.resend.com/emails";

    public static void send(String to, String subject, String textBody) throws Exception {

        String apiKey = System.getenv("RESEND_API_KEY");
        String from = System.getenv("RESEND_FROM");

        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("from", from);
        json.put("to", to);
        json.put("subject", subject);
        json.put("text", textBody);

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new Exception("Email send failed: " + response.body().string());
        }
    }
}
