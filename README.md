# util
java util
冲突测试，这里是冲突的文件。一会儿我们修改了
使用eclipse 解决一个冲突：
1. 修改文件
2. 改好后标志文件**已合并**
3. 所有冲突解决完后，接下来是提交到本地仓库：右击项目，点击commit。
4. 提交后把本地仓库合并到远程仓库：在Git Repositories里右键本地仓库 -> 选择Merger -> 这时候会弹出下面左边的窗口，选择要合并的远程仓库 -> 点击Merge按钮：

　最后再次比较下本地仓库和远程仓库，如果还有本地修改文件未push，直接push即可。上面过程要注意有3点：

1、解决冲突后不要忘记标记已合并；

2、所有冲突都解决且都标记已合并后，不要忘记commit到本地仓库，此时不要点成commit and push了；

3、本地仓库合并远程仓库，是merge，不是push。