select イベント.イベント番号, イベント名称, クリア区分
	from イベント
	join 経験イベント
		on 経験イベント.イベント番号 = イベント.イベント番号
	where タイプ = '1'
