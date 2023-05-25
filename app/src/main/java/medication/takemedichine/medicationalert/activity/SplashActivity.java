package medication.takemedichine.medicationalert.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.QueryPurchasesParams;

import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

import medication.takemedichine.medicationalert.R;
import medication.takemedichine.medicationalert.Utils.Fun;
import medication.takemedichine.medicationalert.Utils.Prefs;

public class SplashActivity extends AppCompatActivity {
    private BillingClient billingClient;
    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        new Fun(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        prefs = new Prefs(this);

        if (Fun.checkInternet()) {
            checkSubscription();

        } else {
            NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(
                    this,
                    getLifecycle()
            );

            DialogPropertiesPendulum properties = builder.getDialogProperties();
            properties.setCancelable(false); // Optional
            properties.setNoInternetConnectionTitle("No Internet"); // Optional
            properties.setNoInternetConnectionMessage("Check your Internet connection and try again"); // Optional
            properties.setShowInternetOnButtons(true); // Optional
            properties.setPleaseTurnOnText("Please turn on"); // Optional
            properties.setWifiOnButtonText("Wifi"); // Optional
            properties.setMobileDataOnButtonText("Mobile data"); // Optional
            builder.build();
        }

    }

    void checkSubscription() {

        billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener((billingResult, list) -> {
        }).build();
        final BillingClient finalBillingClient = billingClient;
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {

            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    finalBillingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    Log.d("testOffer", list.size() + " size");
                                    if (list.size() > 0) {
                                        prefs.setPremium(1);
                                        prefs.setIsRemoveAd(true);
                                        // set 1 to activate premium feature
// set 1 to activate premium feature
                                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                        finish();
                                    } else {
                                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                        finish();
                                        prefs.setPremium(0);
                                        prefs.setIsRemoveAd(false);
// set 0 to de-activate premium feature
                                    }
                                }
                            });

                }

            }
        });
    }

}