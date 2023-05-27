package com.heaven.android.almostcomposegame.ui.components.arena

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.data.model.characters.SkeletonWarrior
import com.heaven.android.almostcomposegame.ui.theme.AlmostComposeGameTheme
import com.heaven.android.almostcomposegame.ui.theme.WhiteMediumAlpha

@Composable
fun CharacterDetails(character: Character) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        val (name, image, damageLayout, abilityLayout, healthLayout, abilityDescr) = createRefs()

        Text(
            text = character.name,
            style = MaterialTheme.typography.body1,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(character.imageId),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(width = 140.dp, height = 190.dp)
                .clip(RoundedCornerShape(15.dp))
                .constrainAs(image) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start)
                }
        )
        CharacterStat(
            icon = R.drawable.health,
            title = stringResource(R.string.health),
            description = character.totalHealth.toString(),
            modifier = Modifier
                .padding(bottom = 8.dp, end = 24.dp)
                .constrainAs(healthLayout) {
                    top.linkTo(image.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                },
        )
        CharacterStat(
            icon = R.drawable.damage,
            title = stringResource(R.string.damage),
            description = character.damage.toString(),
            modifier = Modifier
                .padding(bottom = 8.dp, end = 24.dp)
                .constrainAs(damageLayout) {
                    top.linkTo(healthLayout.bottom)
                    start.linkTo(healthLayout.start)
                },
        )
        CharacterStat(
            icon = R.drawable.ability,
            title = stringResource(R.string.ability),
            description = character.ability.name,
            modifier = Modifier
                .padding(bottom = 8.dp, end = 24.dp)
                .constrainAs(abilityLayout) {
                    top.linkTo(damageLayout.bottom)
                    start.linkTo(damageLayout.start)
                },
        )
        Text(
            text = character.ability.description,
            style = MaterialTheme.typography.caption,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 5,
            modifier = Modifier
                .padding(top = 8.dp, start = 4.dp, end = 4.dp)
                .constrainAs(abilityDescr) {
                    top.linkTo(abilityLayout.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
    }
}

@Composable
fun CharacterStat(
    icon: Int,
    title: String,
    description: String,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = title,
            modifier = Modifier.size(32.dp)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption,
                color = WhiteMediumAlpha,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 13.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
@Preview
fun CharacterDetailsPreview() {
    AlmostComposeGameTheme {
        CharacterDetails(character = SkeletonWarrior())
    }
}