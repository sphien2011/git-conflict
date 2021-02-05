package com.test.hien

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.emv.qrcode.core.model.TagLengthString
import com.emv.qrcode.decoder.mpm.DecoderMpm.decode
import com.emv.qrcode.model.mpm.*
import com.emv.qrcode.validators.Crc16Validate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.test.hien.Utils.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        //TODO
        val additionalDataField: AdditionalDataFieldTemplate = getAddtionalDataField()
        val merchantAccountInformation: MerchantAccountInformationTemplate = getMerchanAccountInformation()
        val merchantAccountInformation1: MerchantAccountInformationTemplate = getMerchanAccountInformation1()
//        val merchantInformationLanguage: MerchantInformationLanguageTemplate = getMerchantInformationLanguage()
//        val unreserved: UnreservedTemplate = getUnreserved()
        val rFUforEMVCo = TagLengthString("65", "9f60dd30-f374-41a6-9667-87b4c7c49430")
        val rFUforEMVCo1 = TagLengthString("66", "MERCHANT_PAYMENT")
        val rFUforEMVCo2 = TagLengthString("67", "queenb")
//        val rFUforEMVCo3 = TagLengthString("68", "abcd2")
//        val rFUforEMVCo4 = TagLengthString("69", "abcd3")

        val merchantPresentMode = MerchantPresentedMode()
        merchantPresentMode.additionalDataField = additionalDataField
        merchantPresentMode.setCountryCode("NG")
        merchantPresentMode.setMerchantCategoryCode("5454")
        merchantPresentMode.setMerchantCity("Lagos")
//        merchantPresentMode.merchantInformationLanguage = merchantInformationLanguage
        merchantPresentMode.setMerchantName("Queen Bee")
        merchantPresentMode.setPayloadFormatIndicator("01")
        merchantPresentMode.setPointOfInitiationMethod("11")
//        merchantPresentMode.setPostalCode("1234567")
//        merchantPresentMode.setTipOrConvenienceIndicator("02")
//        merchantPresentMode.setTransactionAmount("23.72")
        merchantPresentMode.setTransactionCurrency("566")
//        merchantPresentMode.setValueOfConvenienceFeeFixed("500")
        merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation)
//        merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation1)
        merchantPresentMode.addRFUforEMVCo(rFUforEMVCo)
        merchantPresentMode.addRFUforEMVCo(rFUforEMVCo1)
        merchantPresentMode.addRFUforEMVCo(rFUforEMVCo2)
//        merchantPresentMode.addRFUforEMVCo(rFUforEMVCo3)
//        merchantPresentMode.addRFUforEMVCo(rFUforEMVCo4)
//        merchantPresentMode.addUnreserved(unreserved)

        Log.e("TAG", "onCreate: " + merchantPresentMode.toString())

        val bitmap: Bitmap = Utils.encodeAsBitmap(merchantPresentMode.toString(), 1024, 0, Color.BLACK, Color.WHITE)
        findViewById<ImageView>(R.id.imgage).setImageBitmap(bitmap)
        //00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5
        //914BEST TRANSPORT6007BEIJING6107123456762800205678900305098760505abcde0705klm
        //no0805pqres0903tuv1004abcd5016000412340104ijkl64280002ZH0102北京0204最佳运输030
        //4abcd65020080320016A01122334499887707081234567863046325

        val validationResult = Crc16Validate.validate(merchantPresentMode.toString());

        Log.e("TAG", "onCreate2222: " + validationResult.isValid());

        val merchantPresentedMode: MerchantPresentedMode = decode(merchantPresentMode.toString(), MerchantPresentedMode::class.java)

        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.countryCode)
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.merchantCategoryCode.value)
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.merchantCity.value)
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.merchantName.value)
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.payloadFormatIndicator.value)
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.pointOfInitiationMethod.value)
//        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.postalCode.value)
//        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.tipOrConvenienceIndicator.value)
//        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.transactionAmount.value)
//        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.transactionCurrency.value)
//        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.valueOfConvenienceFeeFixed.value)
//        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.merchantAccountInformation.getValue("01"))
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.rfUforEMVCo.get("65")?.value)
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.rfUforEMVCo.get("66")?.value)
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.rfUforEMVCo.get("67")?.value)
//        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.rfUforEMVCo.get("68")?.value)
//        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.rfUforEMVCo.get("69")?.value)
        Log.e("TAG", "onCreate1111: " + merchantPresentedMode.merchantAccountInformation.get("04")?.value?.globallyUniqueIdentifier?.value)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    //0002010102110208476136175204545453035665802NG5909Queen Bee6005Lagos62110707080662365369f60dd30-f374-41a6-9667-87b4c7c494306616MERCHANT_PAYMENT6706queenb63043DD8
    //00020101021102120008476136175204545453035665802NG5909Queen Bee6005Lagos62110707080662365369f60dd30-f374-41a6-9667-87b4c7c494306616MERCHANT_PAYMENT6706queenb63044EE9
}