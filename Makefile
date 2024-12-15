# Makefile

# デフォルトのターゲット
all: create_diary_file

# 現在の日付を取得するための変数
DATE := $(shell date +%Y%m%d)
DATETIME := $(shell date +"%Y/%m/%d %H:%M")

# diary フォルダが存在しない場合に作成するターゲット
create_diary_file: diary/$(DATE).txt

diary/$(DATE).txt: | diary
	echo "Start at $(DATETIME)" > diary/$(DATE).txt

# diary フォルダを作成するターゲット
diary:
	mkdir -p diary

end:
	echo "End at $(DATETIME)" >> diary/$(DATE).txt

# クリーニング用のターゲット
clean:
	rm -f diary/*.txt