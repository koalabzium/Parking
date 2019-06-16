package com.zosiaowsiak.parking.hash;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.MessageDigest;

public class Util
{
    public static final String BASE64_ENCODING = "BASE64";
    public static final String BASE16_ENCODING = "HEX";
    public static final String RFC2617_ENCODING = "RFC2617";
    /**
     The ASCII printable characters the MD5 digest maps to for RFC2617
     */
    private static char[] MD5_HEX = "0123456789abcdef".toCharArray();




    public static String createPasswordHash(String hashAlgorithm, String hashEncoding,
                                            String hashCharset, String username, String password)
    {
        byte[] passBytes;
        String passwordHash = null;

        // convert password to byte data
        try
        {
            if(hashCharset == null)
                passBytes = password.getBytes();
            else
                passBytes = password.getBytes(hashCharset);
        }
        catch(UnsupportedEncodingException uee)
        {
            passBytes = password.getBytes();
        }

        // calculate the hash and apply the encoding.
        try
        {
            MessageDigest md = MessageDigest.getInstance(hashAlgorithm);

            md.update(passBytes);

            byte[] hash = md.digest();
            if(hashEncoding.equalsIgnoreCase(BASE64_ENCODING))
            {
                passwordHash = Util.encodeBase64(hash);
            }
            else if(hashEncoding.equalsIgnoreCase(BASE16_ENCODING))
            {
                passwordHash = Util.encodeBase16(hash);
            }
            else if(hashEncoding.equalsIgnoreCase(RFC2617_ENCODING))
            {
                passwordHash = Util.encodeRFC2617(hash);
            }

        }
        catch(Exception e)
        {
            System.out.println("error calculating hash");;
        }
        return passwordHash;
    }

    /**
     3.1.3 Representation of digest values
     An optional header allows the server to specify the algorithm used to create
     the checksum or digest. By default the MD5 algorithm is used and that is the
     only algorithm described in this document.
     For the purposes of this document, an MD5 digest of 128 bits is represented
     as 32 ASCII printable characters. The bits in the 128 bit digest are
     converted from most significant to least significant bit, four bits at a time
     to their ASCII presentation as follows. Each four bits is represented by its
     familiar hexadecimal notation from the characters 0123456789abcdef. That is,
     binary 0000 getInfos represented by the character '0', 0001, by '1', and so
     on up to the representation of 1111 as 'f'.

     @param data - the raw MD5 hash data
     @return the encoded MD5 representation
     */
    public static String encodeRFC2617(byte[] data)
    {
        char[] hash = new char[32];
        for (int i = 0; i < 16; i++)
        {
            int j = (data[i] >> 4) & 0xf;
            hash[i * 2] = MD5_HEX[j];
            j = data[i] & 0xf;
            hash[i * 2 + 1] = MD5_HEX[j];
        }
        return new String(hash);
    }

    /**
     * Hex encoding of hashes, as used by Catalina. Each byte is converted to
     * the corresponding two hex characters.
     */
    public static String encodeBase16(byte[] bytes)
    {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++)
        {
            byte b = bytes[i];
            // top 4 bits
            char c = (char)((b >> 4) & 0xf);
            if(c > 9)
                c = (char)((c - 10) + 'a');
            else
                c = (char)(c + '0');
            sb.append(c);
            // bottom 4 bits
            c = (char)(b & 0xf);
            if (c > 9)
                c = (char)((c - 10) + 'a');
            else
                c = (char)(c + '0');
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * BASE64 encoder implementation.
     * Provides encoding methods, using the BASE64 encoding rules, as defined
     * in the MIME specification, <a href="http://ietf.org/rfc/rfc1521.txt">rfc1521</a>.
     */
    public static String encodeBase64(byte[] bytes)
    {
        String base64 = null;
        try
        {
            base64 = Base64Encoder.encode(bytes);
        }
        catch(Exception e)
        {
        }
        return base64;
    }

    // These functions assume that the byte array has MSB at 0, LSB at end.
    // Reverse the byte array (not the String) if this is not the case.
    // All base64 strings are in natural order, least significant digit last.

}