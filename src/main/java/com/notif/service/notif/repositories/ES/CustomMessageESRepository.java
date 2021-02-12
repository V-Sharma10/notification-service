package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchPhraseModel;
import org.springframework.data.domain.Page;

public interface CustomMessageESRepository {
     Page<MessageESModel> getByPhrase(SearchPhraseModel phrase);
}
