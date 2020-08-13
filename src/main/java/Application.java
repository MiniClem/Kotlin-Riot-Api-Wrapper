import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;
import services.ClientApi;
import services.PlatformRoutes;
import services.endpoints.ChampionInfo;
import services.interceptors.TokenProvider;

public class Application {
    public static void main(String[] args) {
        ClientApi.INSTANCE.setTokenProvider(new TokenProvider() {
            @NotNull
            @Override
            public String getToken() {
                return "YOUR API KEY";
            }
        });

        ClientApi.INSTANCE.championV3(PlatformRoutes.EUW1).getChampionRotations().enqueue(new retrofit2.Callback<ChampionInfo>() {
            @Override
            public void onResponse(@NotNull Call<ChampionInfo> call, @NotNull Response<ChampionInfo> response) {
                // Use your response data
            }

            @Override
            public void onFailure(@NotNull Call<ChampionInfo> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
