package io.swisschain.services;

import org.bouncycastle.crypto.digests.SHA256Digest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HashService {

  public String computeHash(String value) {
    if (value == null || value.isEmpty())
      throw new IllegalArgumentException("Value cannot be null or empty. Parameter name: value.");

    byte[] data = value.getBytes(StandardCharsets.UTF_8);
    byte[] hash = computeHash(data);
    return Base64.getEncoder().encodeToString(hash);
  }

  public byte[] computeHash(byte[] data) {
    if (data == null || data.length == 0)
      throw new IllegalArgumentException("Value cannot be null or empty. Parameter name: data.");

    SHA256Digest digest = new SHA256Digest();
    digest.update(data, 0, data.length);
    byte[] result = new byte[digest.getDigestSize()];
    digest.doFinal(result, 0);
    return result;
  }
}
