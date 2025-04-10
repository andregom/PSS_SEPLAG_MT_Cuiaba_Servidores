dev:
	docker-compose -f docker-compose.yml -f docker-compose.override.yml up --build

prod:
	docker-compose up --build
