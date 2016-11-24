package android.marshon.likequanmintv.librarys.http.converter;

/*import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;*/




import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class JsonConverterFactory extends Converter.Factory {

    public static JsonConverterFactory create() {
        return new JsonConverterFactory ();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JsonResponseBodyConverter<Type>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        return new JsonRequestBodyConverter<Type>();
    }

    /*public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new JsonResponseBodyConverter<JSONObject>();
    }

    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new JsonRequestBodyConverter<JSONObject>();
    }*/
}
