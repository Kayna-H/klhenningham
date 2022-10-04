// The following was provided by Adam DoupÃ© (https://adamdoupe.com)

import java.util.Base64;

public class Julius {
	
   static String ciphertext = "QFwFUkBXQAVDSldGQEEFUUoFRkRRRk0FUU1ABUFAUgVHXAVLTE"
   		+ "JNUQVSTUxGTQVDQElJBUpLBVFNQAVNTEFAVgVRTURRBUZKU0BX"
   		+ "QEEFUU1ATFcFQUBGTlYeBVxAUQVESUkFUU1AVkAFQUxDQ0xGUE"
   		+ "lRTEBWBVFNQFwFR0pXQAVVRFFMQEtRSVwFREtBBVJMUU1KUFEF"
   		+ "V0BVTEtMS0IJBURLQQVRTUpQQk1RBVFNQFwFSlBCTVEFS0pRBV"
   		+ "FKBUlARFNABVFNQAVWTUpXQFYFREtBBU1EV0dKUFdWBUNXQEAF"
   		+ "Q1dKSAVHSUpGTkRBQAsFZ1BRBVJNQEsFUU1AXAVSQFdABVZQQ0"
   		+ "NAV0xLQgVQS0FAVwVRTUAFQUxWUVdAVlYFUk1MRk0FbAVNRFNA"
   		+ "BUhAS1FMSktAQQkFREtBBWlMR0oFTURBBU9KTEtAQQVnTEdQSV"
   		+ "BWCQVRTUBcBUdKUU0FRkRJSUBBBUNXSkgFSksFVk1MVQhHSkRX"
   		+ "QQVRSgVoRFdGUFYFZEZMSUxQVgVES0EFdlFEUUxQVgVoRFdGUF"
   		+ "YJBVFNQAVJTEBQUUBLREtRVgkFSktABUpDBVJNSkgFRkpISERL"
   		+ "QUBBBVFNQAVRSlJLCQVRTUAFSlFNQFcFUU1ABUJQRFdBVgVKSw"
   		+ "VRTUAFRkpEVlEJBVFNRFEFUU1AXAVSTFZNQEEFUUoFVlVARE4F"
   		+ "UUoFZkRAVkRXBUpLBURDQ0RMV1YFSkMFTEhVSldRREtGQAkFTE"
   		+ "MFVUBXSExWVkxKSwVWTUpQSUEFR0AFQldES1FAQQVRTUBICwVx"
   		+ "TUBcBURBQQVWSkhAUU1MS0IFQ1BXUU1AVwVRSgVWUVdAS0JRTU"
   		+ "BLBVFNQAVMSFVXQFZWTEpLBVFNRFEFUU1AXAVMS1FAS0FAQQVR"
   		+ "SgVRV0BEUQVER0pQUQVESwVERkZKSEhKQURRTEpLCwVsSwVRTU"
   		+ "AFSEBES1FMSEAFUU1AXAVXQFRQQFZRQEEFRAVRV1BGQAkFREtB"
   		+ "BUpHUURMS0BBBUxRBUNXSkgFUU1ASB4FQ0pXBVJNRFEFUU1AXA"
   		+ "VVV0pVSlZAQQVWQEBIQEEFUUoFR0AFSkMFTEhVSldRREtGQAkF"
   		+ "REtBBUxRBVJEVgVSQElJBU5LSlJLBVFNRFEFZkRAVkRXBUFAVk"
   		+ "xXQEEFTFEFREdKU0AFRElJBVFNTEtCVgkFREtBBUxRBVJEVgVM"
   		+ "SERCTEtAQQVRTURRBVZKSEAFREFTREtRREJABVJKUElBBUdABU"
   		+ "FAV0xTQEEFQ1dKSAVnTEdQSVBWAlYFVVdKVUpWRElWCy8vfXNs"
   		+ "C8elsWZEQFZEVwVNRFNMS0IFVkBRBUpQUQVSTFFNBUpLQAVJQE"
   		+ "JMSksFUUoFQkRMSwVVSlZWQFZWTEpLBUpDBVFNQAVISldABVdA"
   		+ "SEpRQAVWUURRQFYJBURLQQVRSgVVV0pTTEFABUZKV0sJBUpDBV"
   		+ "JNTEZNBU1ABU1EQQVHUFEFRAVWSERJSQVUUERLUUxRXAkFUkRW"
   		+ "BURRBVFNTFYFUUxIQAVEUQVnUFFNV0pRUEgJBUpVVUpWTFFABV"
   		+ "FKBWZKV0ZcV0QLBXFNQFdABVdARkBMU0xLQgVkRkxJTFBWBURL"
   		+ "QQVoRFdGUFYCVgVJQFFRQFdWCQVMS0NKV0hMS0IFTUxIBUpDBW"
   		+ "lMR0oCVgVES0EFZ0xHUElQVgJWBUFASERLQVYJBU1ABUlAQ1EF"
   		+ "TUxWBUlAQkxKSwVHQE1MS0EFTUxICQVES0EFV0BRUFdLQEEFTU"
   		+ "xIVkBJQwVRSgVqV0xGUEgLBXJNQEsFTUAFRFdXTFNAQQkFUU1A"
   		+ "XAVSQFdABUxLU0xRQEEFUUoFRAVGSktDQFdAS0ZACwVpTEdKBU"
   		+ "ZESEAFREtBBUhEQUAFREsFRFVKSUpCXAVDSlcFZ0xHUElQVgkF"
   		+ "B1FNRFEFTUAFUkRWBUQFSERLBUpDBVZRV0pLQgVVRFZWTEpLCQ"
   		+ "VES0EFTURBBUQFVVdMU0RRQAVUUERXV0BJBURCRExLVlEFZkRA"
   		+ "VkRXCQVGSktRV0RGUUBBBVJNQEsFTUAFUkRWBURAQUxJQAVES0"
   		+ "EFVVdEQFFKVx4FUU1EUQVDSlcFUU1MVgVXQERWSksFTUAFTURB"
   		+ "BURTSkxBQEEFUU1ABUZKS0NAV0BLRkAJBUlAVlEFRENDRExXVg"
   		+ "VKQwVRTUAFUFFISlZRBUxIVUpXUURLRkAFREtBBURBU0RLUURC"
   		+ "QAVITEJNUQVHQAVMSFVAQUBBBUdcBVFNQAVSRFdIUU0FSkMFTU"
   		+ "xWBVFASFVAVwsFcU1EUQVMUQVLSlIFUkRWBURLQQVAU0BXBU1E"
   		+ "QQVHQEBLBXVKSFVAXAJWBUhKVlEFQERXS0BWUQVSTFZNCQVRTU"
   		+ "RRBVFNQFwFVk1KUElBBUdABVdARkpLRkxJQEEJBURLQQVJRFwF"
   		+ "QUpSSwVRTUBMVwVEV0hWHgVHUFEFUU1AXAVSQFdABUtKUQVEUF"
   		+ "FNSldMX0BBBVFKBVFXQERRBUpLBVFNRFEFVlBHT0BGUQkFR0BG"
   		+ "RFBWQAVRTUBcBVdAVkxCS0BBBVFNQAVSTUpJQAVIREtEQkBIQE"
   		+ "tRBUpDBVFNQAVSRFcJBURLQQVESUkFSlFNQFcFSERRUUBXVgkF"
   		+ "UUoFdUpIVUBcCQVHXAVKV0FAVwVKQwVRTUAFRkpQS0ZMSQsFZ1"
   		+ "BRBVJNQEsFUU1AXAVSQA=="
   		+ "";

   public static String encode (byte[] text)
   {
	  return Base64.getEncoder().encodeToString(text);
   }

   public static byte[] decode (String text)
   {
	  return Base64.getDecoder().decode(text);
   }

   public static byte[] encrypt(byte[] cleartext, byte key)
   {
	  byte[] toReturn = new byte[cleartext.length];
	  for (int i = 0; i < cleartext.length; i++)
	  {
		 toReturn[i] = (byte) (cleartext[i] ^ key);
	  }
	  return toReturn;
   }

   public static void main(String[] args)
   {
//	  System.out.println(encode(encrypt("test".getBytes(), (byte)0x80)));
//	  System.out.println(new String((encrypt(decode("9OXz9A=="), (byte)0x80))));
	  
	  //loop through all hex
	  //byte[] test;
//	  int k = 0;
//	  for (int i = 0x00; i < 0xff; i++) {
//		  //try each as the key
//		  //test = (encrypt(decode(ciphertext), (byte)0x80));
//		  k++;
//		  System.out.println(k + new String((encrypt(decode(ciphertext), (byte)i))));
//	  }
	  
	  //decryptbruteforce(ciphertext);
	   
//	  for (int i = 0x00; i < 0x41; i++) {
//		  System.out.println(i);
//		  System.out.println(new String((encrypt(decode(ciphertext), (byte)i))));
//	  }
	  
	  System.out.println(new String((encrypt(decode(ciphertext), (byte)0x05))));

	  System.out.println(new String((encrypt(decode(ciphertext), (byte)37))));
	  
	  //System.out.println((byte)0x48);

//	  print(encrypt(decode("9OXz9A=="), 0x80))
//	  b'test'
	  
	  //print the ones with actual words in it
   }
   
   public static void decryptbruteforce(String encryptmessage) {

	    String standalpha = " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
	    //String encryptmessageupper = encryptmessage.toUpperCase();
	    String encryptmessageupper = encryptmessage;
	    StringBuilder sbdecrypt = new StringBuilder(encryptmessageupper);
	    
	    int key;
	    int i;
	    int index;
	    char currentchar;
	    char newchar;

	    for (key = 0; key < 256; key++) {
	      //Ensure you do your shift on the same string each time.
	      //This makes a new object each time
	      sbdecrypt = new StringBuilder(encryptmessageupper);
	      System.out.println("lenght" + sbdecrypt.length());
	      for (i = 0; i < sbdecrypt.length(); i++) {
	        currentchar = sbdecrypt.charAt(i);
	        index = standalpha.indexOf(currentchar);
	        if (index != -1) {
	          index = index - key;
	          if (index < 0) {
	            index = index + 26;
	          }
	          newchar = standalpha.charAt(index);
	          sbdecrypt.setCharAt(i, newchar);
	        }
	      }
	      System.out.println("Key: " + key + " Decrypted String: " + sbdecrypt);
	    }
	  }
}