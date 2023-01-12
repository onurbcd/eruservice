package com.onurbcd.eruservice.util;

import com.onurbcd.eruservice.persistency.entity.Document;
import com.onurbcd.eruservice.service.enums.Error;
import com.onurbcd.eruservice.service.exception.ApiException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DocumentUtil {

    private static final int RADIX = 16;

    public static String generateHash(Document document) {
        try {
            var transformedName = document.getName() + document.getPath() + document.getMimeType() +
                    document.getSize() + new Date().getTime();

            var messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(transformedName.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, messageDigest.digest()).toString(RADIX);
        } catch (NoSuchAlgorithmException e) {
            throw new ApiException(Error.DOCUMENT_GENERATE_HASH, e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
