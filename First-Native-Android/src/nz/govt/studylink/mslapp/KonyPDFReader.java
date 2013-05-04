package nz.govt.studylink.mslapp;

import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.konylabs.android.KonyMain;

public class KonyPDFReader{
	
	public KonyPDFReader() {
	}
	
	public static void showPDFPage(String URL) {
		
//		Intent mainIntent = new Intent(Intent.ACTION_VIEW, null);
//        PackageManager pm = KonyMain.getActivityContext().getPackageManager();
//        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);
//        
		Intent pdfIntent;
		KonyMain context = KonyMain.getActivityContext();
		if (context != null) {
			pdfIntent = new Intent(context, AndroidPDFActivity.class);
			pdfIntent.setAction(Intent.ACTION_VIEW);
			pdfIntent.setDataAndType(Uri.parse(URL), "application/pdf");
			context.startActivity(pdfIntent);
		}
	}
	
	public static String returnString() {
		return "KONY test";
	}

}
