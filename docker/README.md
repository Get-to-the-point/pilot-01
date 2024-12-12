# 도커 사용

## 실행

```bash
docker-compose up
```

```bash
docker-compose -f ./docker/docker-compose.yml up
```

## 종료

```bash
docker-compose down
```

```bash
docker-compose -f ./docker/docker-compose.yml down
```

## 볼륨 삭제

```bash
docker volume prune --all
```