package com.rizkyjayusman.tinyurl.documents;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter @Setter
@Document("TinyUrl")
public class TinyUrl {
    private @Id ObjectId id;
    @Indexed(name = "uid")
    private String uid;
    private String originalUrl;
    @Indexed(name = "uniqueCode", unique = true)
    private String uniqueCode;
    @Indexed(name = "createdDate", expireAfter = "5s")
    private Date createdDate;
}
