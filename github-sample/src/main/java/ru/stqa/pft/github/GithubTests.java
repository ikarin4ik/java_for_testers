package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test

    public void testCommits() throws IOException {
        Github github = new RtGithub("76abf09f92e1275062fac50621abf4d506d0a768");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("ikarin4ik", "java_for_testers")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
