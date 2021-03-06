package co.lilpilot.blog.controller;

import co.lilpilot.blog.model.Tag;
import co.lilpilot.blog.service.TagService;
import co.lilpilot.blog.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lilpilot on 2017/5/25.
 */
@RestController
@RequestMapping("/api/v1/admin")
public class AdminTagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/tags")
    @ApiOperation(value = "创建新标签")
    @ApiImplicitParam(name = "tag", value = "标签类", required = true, dataType = "Tag", paramType = "body")
    public Result<Tag> createTag(@RequestBody Tag tag) {
        return Result.success(tagService.saveOrUpdate(tag));
    }

    @PutMapping("/tags/{id}")
    @ApiOperation(value = "更新标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "tag", value = "标签类", required = true, dataType = "Tag", paramType = "body")
    })
    public Result<Tag> updateTag(
            @PathVariable Long id,
            @RequestBody Tag tag) {
        return Result.success(tagService.saveOrUpdate(tag));
    }

}
