package ru.pachan.writer.service.writerDtoConsumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pachan.writer.dto.WriterDto;
import ru.pachan.writer.model.Notification;
import ru.pachan.writer.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WriterDtoConsumerImpl implements WriterDtoConsumer {

    private final NotificationRepository repository;

    @Transactional
    @Override
    public void accept(List<WriterDto> writerDtoList) {
        writerDtoList.forEach(it -> {
            Optional<Notification> notification = repository.findByPersonId(it.personId());
            if (notification.isPresent()) {
                notification.get().setCount(notification.get().getCount() + it.count());
                repository.save(notification.get());
            } else {
                Notification newNotification = new Notification();
                newNotification.setPersonId(it.personId());
                newNotification.setCount(it.count());
                repository.save(newNotification);
            }
        });
    }
}
