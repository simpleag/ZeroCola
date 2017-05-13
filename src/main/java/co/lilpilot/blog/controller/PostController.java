package co.lilpilot.blog.controller;

import co.lilpilot.blog.model.Post;
import co.lilpilot.blog.service.PostService;
import co.lilpilot.blog.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lilpilot on 2017/5/8.
 */
@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    @ApiOperation(value = "获取所有文章信息（打开状态）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条目数", defaultValue = "10", dataType = "Integer", paramType = "query")
    })
    public Result<Page<Post>> getAllOpenPosts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        page = page < 1 ? 0 : page - 1;
        return Result.success(postService.getAllOpenPosts(page, pageSize));
    }

    @GetMapping("/posts/{id}")
    @ApiOperation(value = "获取指定文章信息", notes = "根据文章id获取文章信息")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Long", paramType = "path")
    public Result<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getById(id);
        if (post == null) {
            return Result.fail("500", "文章不存在");
        }
        return Result.success(post);
    }

    @PostMapping("/posts")
    @ApiOperation(value = "创建文章")
    public Result<Post> createPost(@RequestBody Post post) {
        return Result.success(postService.saveOrUpdate(post));
    }

    @PutMapping("/posts")
    @ApiOperation(value = "更新文章")
    public Result<Post> updatePost(@RequestBody Post post) {
        return Result.success(postService.saveOrUpdate(post));
    }

}
