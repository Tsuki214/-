# AutoSpin (Minecraft 1.21.11 Fabric)

一个简单的纯客户端 Fabric Mod，开启后可以让玩家在原地平滑持续旋转。

## 功能与默认按键

可以在游戏内 **选项 -> 控制 -> 按键绑定 -> 自动转圈 (AutoSpin)** 中自由修改：

- **R**：开启 / 关闭自动转圈
- **]**（右中括号）：加快转圈速度（每次 +3.0°/tick，最高 60.0°/tick）
- **[**（左中括号）：减慢转圈速度（每次 -3.0°/tick，最低 2.0°/tick）

默认转速为 `12.0°/tick`（每秒约转 240°）。打开聊天栏、背包或暂停菜单时会自动暂停旋转。

## 本地编译生成 `.jar`

1. 确保本地已安装 **JDK 21** 以及 **Gradle 9.4+**（或直接使用支持 Gradle 的 IntelliJ IDEA 打开本目录）。
2. 在项目根目录打开终端，运行：
   ```bash
   gradle build
   ```
   （如果本机还没有全局安装 Gradle，可以先在根目录执行一次 `gradle wrapper` 或用 IntelliJ IDEA 自动下载 Gradle Wrapper 后再运行 `./gradlew build`。）
3. 编译完成后，在 `build/libs/` 目录下找到 `autospin-1.0.0.jar`（注意不要选带 `-dev` 或 `-sources` 后缀的文件）。
4. 将 `autospin-1.0.0.jar` 和对应版本的 **Fabric API (`0.141.6+1.21.11`)** 一起放入 `.minecraft/mods` 文件夹即可使用。
