package com.rizkyjayusman.tinyurl.service;

import com.rizkyjayusman.tinyurl.documents.TinyUrl;
import com.rizkyjayusman.tinyurl.documents.repository.TinyUrlRepository;
import com.rizkyjayusman.tinyurl.helper.EncryptHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TinyUrlService {

    private final TinyUrlRepository tinyUrlRepository;

    public String encode(String url) {
        TinyUrl tinyUrl = new TinyUrl();
        tinyUrl.setOriginalUrl(url);
        tinyUrl.setUniqueCode(EncryptHelper.encrypt(url));
        tinyUrl.setCreatedDate(new Date());
        tinyUrlRepository.save(tinyUrl);

        return tinyUrl.getUniqueCode();
    }

    public String decode(String uniqueCode) {
        Optional<TinyUrl> tinyUrl = tinyUrlRepository.findByUniqueCode(uniqueCode);
        return tinyUrl.map(TinyUrl::getOriginalUrl).orElse(null);
    }
}
