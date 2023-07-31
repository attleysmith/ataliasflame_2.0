drop table if exists character cascade;
drop table if exists character_attribute_mapping cascade;
drop table if exists defensive_god_conversion_log cascade;
drop table if exists level cascade;
create table character (name varchar(255) not null, armor_code varchar(255), armor_defense integer, armor_popularity integer, attack integer, attribute_points integer, caste varchar(255), damage_multiplier integer, defense integer, defensive_god varchar(255), experience integer, gender varchar(255), initiative integer, injury integer, level integer, max_damage integer, min_damage integer, race varchar(255), shield_code varchar(255), shield_defense integer, shield_popularity integer, total_health integer, total_magic_point integer, used_magic_point integer, weapon_code varchar(255), weapon_defense integer, weapon_initiative integer, weapon_max_damage integer, weapon_min_damage integer, weapon_one_handed boolean, weapon_popularity integer, primary key (name));
create table character_attribute_mapping (character_name varchar(255) not null, attribute_value integer, attribute_code smallint not null, primary key (character_name, attribute_code));
create table defensive_god_conversion_log (conversion_code varchar(255) not null, god varchar(255), cleric_id varchar(255) not null, converted_character_id varchar(255), primary key (conversion_code));
create table level (id integer not null, next_level_experience integer, primary key (id));
alter table if exists character_attribute_mapping add constraint FKm6mebc501x3p71w9nrvgabl7o foreign key (character_name) references character;
alter table if exists defensive_god_conversion_log add constraint FKsli5xm4nk5chfqqxiiscpdbjh foreign key (cleric_id) references character;
alter table if exists defensive_god_conversion_log add constraint FK5npohb4os11fat6frixnpn97p foreign key (converted_character_id) references character;
