package co.lilpilot.blog.service;

import co.lilpilot.blog.model.Tag;
import co.lilpilot.blog.repository.TagRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by lilpilot on 2017/5/3.
 */
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Page<Tag> getAllTags(Integer page, Integer pageSize) {
        return tagRepository.findAll(new PageRequest(page, pageSize));
    }

    public Tag getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return tagRepository.findOne(id);
    }

    public Tag getByName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("name is null");
        }
        return tagRepository.findByName(name);
    }

    public Tag saveOrUpdate(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("tag is null");
        }
        return tagRepository.save(tag);
    }
}
