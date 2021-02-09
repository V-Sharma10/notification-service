package com.notif.service.notif.models.request.imiconnect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalSmsRequest {

    @JsonProperty("deliverychannel")
    private String deliveryChannel;

    @JsonProperty("channels")
    private Channels channels;

    @JsonProperty("destination")
    private List<Destination> destination;

}
