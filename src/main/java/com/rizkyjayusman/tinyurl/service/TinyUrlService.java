package com.rizkyjayusman.tinyurl.service;

import com.mongodb.DuplicateKeyException;
import com.rizkyjayusman.tinyurl.documents.TinyUrl;
import com.rizkyjayusman.tinyurl.documents.repository.TinyUrlRepository;
import com.rizkyjayusman.tinyurl.helper.EncryptHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TinyUrlService {

    private final TinyUrlRepository tinyUrlRepository;

    public String encode(String url) {
        TinyUrl tinyUrl = new TinyUrl();
        tinyUrl.setUid(UUID.randomUUID().toString());
        tinyUrl.setOriginalUrl(url);
        tinyUrl.setUniqueCode(EncryptHelper.encrypt(url));
        tinyUrl.setCreatedDate(new Date());

        try {
            tinyUrlRepository.save(tinyUrl);
        } catch (DuplicateKeyException e) {
            log.error("TinyUrlService.encode() :: duplicate unique code value :: {}", tinyUrl.getUniqueCode());
            return encode(url);
        }

        return tinyUrl.getUniqueCode();
    }

    public String decode(String uniqueCode) {
        Optional<TinyUrl> tinyUrl = tinyUrlRepository.findByUniqueCode(uniqueCode);
        return tinyUrl.map(TinyUrl::getOriginalUrl).orElse(null);
    }
}
