# Alipay SDK Test Project

这是一个使用支付宝开放平台 SDK 的示例项目。

## 环境要求

- JDK 11 或更高版本
- Maven 3.6 或更高版本
- direnv (用于管理环境变量)

## 配置说明

项目使用 `.envrc` 文件管理支付宝配置信息。在使用之前，需要：

1. 安装 direnv：
```bash
# macOS
brew install direnv

# 在 ~/.zshrc 或 ~/.bashrc 中添加：
eval "$(direnv hook zsh)"  # 或 bash
```

2. 允许项目目录使用 .envrc：
```bash
direnv allow
```

3. 配置支付宝参数：
- 复制 `.envrc.example` 为 `.envrc`
- 在 `.envrc` 中填入你的支付宝配置信息：
  - ALIPAY_SERVER_URL：支付宝网关地址
  - ALIPAY_APP_ID：应用 ID
  - ALIPAY_PRIVATE_KEY：应用私钥
  - ALIPAY_PUBLIC_KEY：支付宝公钥

## 构建和运行

1. 编译项目：
```bash
mvn clean package
```

2. 运行项目：
```bash
java -jar target/alipay-sdk-test-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## 注意事项

- 请确保已经开通了支付宝开放平台账号
- 请妥善保管私钥信息，不要泄露
- 建议在正式环境中使用配置文件管理密钥信息，而不是硬编码在代码中
- 确保 `.envrc` 文件不会被提交到版本控制系统（已添加到 .gitignore）
