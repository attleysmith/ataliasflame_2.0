package com.asgames.ataliasflame.interfaces.mappers;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.interfaces.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class CharacterDtoMapper {

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "race", source = "race")
    @Mapping(target = "caste", source = "caste")
    @Mapping(target = "defensiveGod", source = "defensiveGod")
    @Mapping(target = "attack", source = "attack")
    @Mapping(target = "defense", source = "defense")
    @Mapping(target = "minDamage", source = "minDamage")
    @Mapping(target = "maxDamage", source = "maxDamage")
    @Mapping(target = "damageMultiplier", source = "damageMultiplier")
    @Mapping(target = "initiative", source = "initiative")
    @Mapping(target = "level", source = "level")
    @Mapping(target = "experience", source = "experience")
    @Mapping(target = "attributePoints", source = "attributePoints")
    @Mapping(target = "totalHealth", source = "health.totalEnergy")
    @Mapping(target = "injury", source = "health.usedEnergy")
    @Mapping(target = "totalMagicPoint", source = "magic.totalEnergy")
    @Mapping(target = "usedMagicPoint", source = "magic.usedEnergy")
    @Mapping(target = "attributes", source = "attributes")
    @Mapping(target = "soulChips", source = "soulChips")
    @Mapping(target = "companions", source = "companions")
    @Mapping(target = "blessings", source = "blessings")
    @Mapping(target = "weapon", source = "weapon")
    @Mapping(target = "shield", expression = "java(character.getShield().map(shield -> toShieldDto(shield)).orElse(null))")
    @Mapping(target = "cover", source = "cover")
    @Mapping(target = "locationReference", source = "location.reference")
    public abstract CharacterDto toCharacterDto(Character character);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "shape", source = "shape")
    @Mapping(target = "attack", source = "attack")
    @Mapping(target = "defense", source = "defense")
    @Mapping(target = "minDamage", source = "minDamage")
    @Mapping(target = "maxDamage", source = "maxDamage")
    @Mapping(target = "initiative", source = "initiative")
    @Mapping(target = "upgradedCaste", source = "upgradedCaste")
    @Mapping(target = "effectiveness", source = "effectiveness")
    @Mapping(target = "totalHealth", source = "health.totalEnergy")
    @Mapping(target = "fatigue", source = "health.usedEnergy")
    public abstract SoulChipDto toSoulChipDto(SoulChip soulChip);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "attack", source = "attack")
    @Mapping(target = "defense", source = "defense")
    @Mapping(target = "minDamage", source = "minDamage")
    @Mapping(target = "maxDamage", source = "maxDamage")
    @Mapping(target = "initiative", source = "initiative")
    @Mapping(target = "totalHealth", source = "health.totalEnergy")
    @Mapping(target = "injury", source = "health.usedEnergy")
    @Mapping(target = "sourceSoulChip", source = "companion", qualifiedByName = "companionSource")
    public abstract CompanionDto toCompanionDto(Companion companion);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "booster", source = "booster")
    @Mapping(target = "sourceSoulChip", source = "source.reference")
    public abstract ActiveBlessingDto toActiveBlessingDto(ActiveBlessing activeBlessing);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "minDamage", source = "minDamage")
    @Mapping(target = "maxDamage", source = "maxDamage")
    @Mapping(target = "defense", source = "defense")
    @Mapping(target = "initiative", source = "initiative")
    @Mapping(target = "oneHanded", source = "oneHanded")
    public abstract WeaponDto toWeaponDto(Weapon weapon);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "blocking", source = "blocking")
    @Mapping(target = "defense", source = "defense")
    @Mapping(target = "absorption", source = "absorption")
    @Mapping(target = "durability", source = "durability.totalEnergy")
    @Mapping(target = "wearAndTear", source = "durability.usedEnergy")
    public abstract ShieldDto toShieldDto(Shield shield);

    @Mapping(target = "helmet", expression = "java(cover.getHelmet().map(armor -> toArmorDto(armor)).orElse(null))")
    @Mapping(target = "bodyArmor", expression = "java(cover.getBodyArmor().map(armor -> toArmorDto(armor)).orElse(null))")
    @Mapping(target = "energyArmor", expression = "java(cover.getEnergyArmor().map(armor -> toArmorDto(armor)).orElse(null))")
    @Mapping(target = "divineArmor", expression = "java(cover.getDivineArmor().map(armor -> toArmorDto(armor)).orElse(null))")
    public abstract CoverDto toCoverDto(Cover cover);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "defense", source = "defense")
    @Mapping(target = "absorption", source = "absorption")
    @Mapping(target = "armorType", source = "armorType")
    @Mapping(target = "durability", source = "durability.totalEnergy")
    @Mapping(target = "wearAndTear", source = "durability.usedEnergy")
    public abstract ArmorDto toArmorDto(Armor armor);

    @Named("companionSource")
    public static String companionSource(Companion companion) {
        return (companion instanceof SummonedSoulChip) ? ((SummonedSoulChip) companion).getSource().getReference() : null;
    }
}
